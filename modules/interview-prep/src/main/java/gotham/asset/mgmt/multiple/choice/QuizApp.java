package gotham.asset.mgmt.multiple.choice;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.file.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * REPL-style console quiz application with "test" and "study" modes.
 * Dynamically discovers all Question subclasses via classpath scanning.
 * Shuffles question order and answer order each run.
 */
public class QuizApp {

    private static final String SEPARATOR = "=".repeat(80);
    private static final String THIN_SEPARATOR = "-".repeat(80);
    private static final String QUESTION_BASE_PACKAGE = "gotham.asset.mgmt.multiple.choice.questions";
    private static final String CYAN = "\u001B[36m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String DIM = "\u001B[2m";
    private static final String RESET = "\u001B[0m";
    private static final Path SESSIONS_DIR = Paths.get(System.getProperty("user.home"), ".quiz-sessions");
    private static final Path REPORT_DIR = Paths.get("testReports");

    private record MissedQuestion(int displayNum, Question question, String userAnswer, String correctAnswer) {}
    private static class SessionData implements Serializable {
        private static final long serialVersionUID = 1L;
        String id;
        String mode; // test or study
        String focus;
        String subfocus;
        List<String> classNames;
        List<List<String>> choices;
        List<String> questionTexts;
        List<List<String>> shuffledChoices;
        List<Integer> shuffledCorrect;
        List<Integer> correctIndices;
        List<Integer> userAnswers; // -1 for unanswered
        int cursor; // next question index
        long createdAt;
        boolean completed;
    }

    private final Scanner scanner;
    private final List<Question> questions;
    private final Map<String, Question> questionByName;

    public QuizApp(Scanner scanner) {
        this.scanner = scanner;
        this.questions = new ArrayList<>(discoverQuestions());
        this.questionByName = new HashMap<>();
        for (Question q : questions) {
            questionByName.put(q.getClass().getName(), q);
        }
        try {
            Files.createDirectories(SESSIONS_DIR);
            Files.createDirectories(REPORT_DIR);
        } catch (IOException ignored) {}
    }

    /**
     * Discovers all concrete subclasses of Question under the question base package
     * (including subpackages like java/ and SQL/) by scanning the classpath directories.
     */
    private static List<Question> discoverQuestions() {
        List<Question> found = new ArrayList<>();
        String packagePath = QUESTION_BASE_PACKAGE.replace('.', '/');
        try {
            var classLoader = Thread.currentThread().getContextClassLoader();
            var resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                var resource = resources.nextElement();
                if (resource.getProtocol().equals("file")) {
                    File dir = new File(resource.toURI());
                    if (dir.isDirectory()) {
                        scanDirectory(dir, QUESTION_BASE_PACKAGE, found);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: could not scan for questions: " + e.getMessage());
        }
        found.sort(Comparator.comparing(q -> q.getClass().getSimpleName()));
        return found;
    }

    private static void scanDirectory(File dir, String packageName, List<Question> found) {
        File[] children = dir.listFiles();
        if (children == null) {
            return;
        }

        for (File file : children) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), found);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");
                try {
                    Class<?> clazz = Class.forName(className);
                    if (Question.class.isAssignableFrom(clazz)
                            && !Modifier.isAbstract(clazz.getModifiers())
                            && !clazz.equals(Question.class)) {
                        found.add((Question) clazz.getDeclaredConstructor().newInstance());
                    }
                } catch (Exception ignored) {
                    // skip classes that can't be instantiated
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizApp app = new QuizApp(scanner);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nInterrupted, exiting.");
        }));
        app.run();
        scanner.close();
    }

    // ── REPL ────────────────────────────────────────────────────────

    private void run() {
        printBanner();
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println(CYAN + SEPARATOR + RESET);
            System.out.println("  Select an option:");
            List<String> options = List.of(
                    "Test (new session)",
                    "Study (new session)",
                    "Load previous session",
                    "Delete a saved session",
                    "Quit"
            );
            int choice = selectFromList(options);
            switch (choice) {
                case 0 -> startNewSession("test");
                case 1 -> startNewSession("study");
                case 2 -> loadSessionAndRun();
                case 3 -> deleteSessionPrompt();
                default -> {
                    System.out.println("\nGoodbye! Happy studying.");
                    running = false;
                }
            }
        }
    }

    private void startNewSession(String mode) {
        SelectionResult sel = selectQuestions();
        if (sel.questions().isEmpty()) return;
        SessionData data = createSessionData(mode, sel.focus(), sel.subfocus(), sel.questions());
        saveSession(data);
        runSession(data);
    }

    private void loadSessionAndRun() {
        List<SessionData> sessions = loadSessions();
        if (sessions.isEmpty()) {
            System.out.println("  No saved sessions found.");
            return;
        }
        List<String> labels = new ArrayList<>();
        for (SessionData s : sessions) {
            String status = s.completed ? "completed" : ("in-progress " + s.cursor + "/" + s.classNames.size());
            labels.add(String.format("%s | %s/%s | %s | %s", s.mode.toUpperCase(), s.focus, s.subfocus, status, new Date(s.createdAt)));
        }
        int idx = selectFromList(labels);
        runSession(sessions.get(idx));
    }

    private void deleteSessionPrompt() {
        List<SessionData> sessions = loadSessions();
        if (sessions.isEmpty()) {
            System.out.println("  No saved sessions to delete.");
            return;
        }
        List<String> labels = new ArrayList<>();
        for (SessionData s : sessions) {
            String status = s.completed ? "completed" : ("in-progress " + s.cursor + "/" + s.classNames.size());
            labels.add(String.format("%s | %s/%s | %s | %s", s.mode.toUpperCase(), s.focus, s.subfocus, status, new Date(s.createdAt)));
        }
        int idx = selectFromList(labels);
        deleteSession(sessions.get(idx));
        System.out.println("  Deleted session.");
    }

    private record SelectionResult(List<Question> questions, String focus, String subfocus) {}

    private SelectionResult selectQuestions() {
        // Build maps: top-level category -> questions, and subcategory map per category
        Map<String, List<Question>> byTop = new HashMap<>();
        Map<String, Map<String, List<Question>>> bySub = new HashMap<>();

        for (Question q : questions) {
            String[] parts = packageSegments(q);
            String top = parts.length > 0 ? parts[0] : "core";
            String sub = parts.length > 1 ? parts[1] : null;

            byTop.computeIfAbsent(top, k -> new ArrayList<>()).add(q);
            if (sub != null && !sub.isBlank()) {
                bySub.computeIfAbsent(top, k -> new HashMap<>())
                        .computeIfAbsent(sub, k -> new ArrayList<>()).add(q);
            }
        }

        String focus = promptChoice("Focus area", byTop.keySet());
        if (focus.equals("all")) {
            return new SelectionResult(distributeEvenly(byTop.values()), "all", "all");
        }

        Map<String, List<Question>> subcats = bySub.getOrDefault(focus, Collections.emptyMap());
        List<Question> pool;
        if (!subcats.isEmpty()) {
            String subFocus = promptChoice("Sub-area for " + focus, subcats.keySet());
            if (subFocus.equals("all")) {
                pool = new ArrayList<>();
                for (List<Question> qs : subcats.values()) {
                    pool.addAll(qs);
                }
            } else {
                pool = subcats.getOrDefault(subFocus, List.of());
            }
            if (pool.isEmpty()) {
                System.out.println("  No questions found for that selection.");
                return new SelectionResult(List.of(), focus, subFocus);
            }
            return new SelectionResult(pickCount(pool), focus, subFocus);
        } else {
            pool = byTop.getOrDefault(focus, List.of());
        }

        if (pool.isEmpty()) {
            System.out.println("  No questions found for that selection.");
            return new SelectionResult(List.of(), focus, "all");
        }

        return new SelectionResult(pickCount(pool), focus, "all");
    }

    private List<Question> pickCount(List<Question> pool) {
        // Deduplicate before counting to avoid user requesting more than unique set
        List<Question> unique = new ArrayList<>(new LinkedHashSet<>(pool));
        Collections.shuffle(unique);
        int count = promptCount(unique.size());
        return new ArrayList<>(unique.subList(0, count));
    }

    private List<Question> distributeEvenly(Collection<List<Question>> pools) {
        List<List<Question>> shuffledPools = new ArrayList<>();
        for (List<Question> q : pools) {
            List<Question> copy = new ArrayList<>(q);
            Collections.shuffle(copy);
            shuffledPools.add(copy);
        }
        int totalAvailable = shuffledPools.stream().mapToInt(List::size).sum();
        int desired = promptCount(totalAvailable);

        List<Question> selection = new ArrayList<>(desired);
        int idx = 0;
        while (selection.size() < desired && !shuffledPools.isEmpty()) {
            List<Question> poolList = shuffledPools.get(idx % shuffledPools.size());
            if (!poolList.isEmpty()) {
                selection.add(poolList.remove(poolList.size() - 1));
            } else {
                shuffledPools.remove(idx % shuffledPools.size());
                idx--;
            }
            idx++;
        }
        Collections.shuffle(selection);
        return selection;
    }

    private String promptChoice(String label, Set<String> options) {
        List<String> opts = new ArrayList<>(options);
        opts.sort(String::compareTo);
        opts.add(0, "all");
        while (true) {
            System.out.println("\n" + CYAN + THIN_SEPARATOR + RESET);
            System.out.println("  " + label + ":");
            for (int i = 0; i < opts.size(); i++) {
                System.out.printf("  %d) %s%n", i + 1, opts.get(i));
            }
            System.out.print("  Choose 1-" + opts.size() + ": ");
            String raw = safeReadLine();
            if (raw == null || raw.trim().isEmpty()) {
                return "all";
            }
            try {
                int n = Integer.parseInt(raw.trim());
                if (n >= 1 && n <= opts.size()) {
                    return opts.get(n - 1);
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("  Invalid choice, try again.");
        }
    }

    private String[] packageSegments(Question q) {
        String pkg = q.getClass().getPackageName();
        if (!pkg.startsWith(QUESTION_BASE_PACKAGE)) {
            return new String[] {"other"};
        }
        String remainder = pkg.substring(QUESTION_BASE_PACKAGE.length());
        if (remainder.startsWith(".")) remainder = remainder.substring(1);
        if (remainder.isEmpty()) return new String[] {"core"};
        return remainder.split("\\.");
    }

    private int promptCount(int max) {
        if (max <= 0) {
            return 0;
        }
        System.out.printf("  How many questions? (1-%d, Enter for all): ", max);
        String rawLine = safeReadLine();
        if (rawLine == null) {
            System.out.println("\nInput closed. Exiting.");
            return 0;
        }
        String raw = rawLine.trim();
        if (raw.isEmpty()) {
            return max;
        }
        try {
            int n = Integer.parseInt(raw);
            if (n < 1) {
                return 1;
            }
            return Math.min(n, max);
        } catch (NumberFormatException e) {
            System.out.println("  Not a number, using all " + max + ".");
            return max;
        }
    }

    private SessionData createSessionData(String mode, String focus, String subfocus, List<Question> qs) {
        SessionData d = new SessionData();
        d.id = String.valueOf(System.currentTimeMillis());
        d.mode = mode;
        d.focus = focus;
        d.subfocus = subfocus;
        d.createdAt = System.currentTimeMillis();
        d.cursor = 0;
        d.completed = false;
        d.classNames = new ArrayList<>();
        d.questionTexts = new ArrayList<>();
        d.choices = new ArrayList<>();
        d.shuffledChoices = new ArrayList<>();
        d.correctIndices = new ArrayList<>();
        d.shuffledCorrect = new ArrayList<>();
        d.userAnswers = new ArrayList<>();
        for (Question q : qs) {
            d.classNames.add(q.getClass().getName());
            d.questionTexts.add(q.getQuestionText());
            d.choices.add(new ArrayList<>(q.getChoices()));
            d.correctIndices.add(q.getCorrectAnswerIndex());
            d.userAnswers.add(-1);
            ShuffledChoices sc = shuffleChoices(q);
            d.shuffledChoices.add(new ArrayList<>(sc.displayChoices()));
            d.shuffledCorrect.add(sc.shuffledCorrectIndex());
        }
        return d;
    }

    private Path sessionPath(String id) {
        return SESSIONS_DIR.resolve("session-" + id + ".ser");
    }

    private void saveSession(SessionData data) {
        Path p = sessionPath(data.id);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(p))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Warning: could not save session: " + e.getMessage());
        }
        writePortalPage();
    }

    private List<SessionData> loadSessions() {
        List<SessionData> list = new ArrayList<>();
        if (!Files.isDirectory(SESSIONS_DIR)) {
            return list;
        }
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(SESSIONS_DIR, "session-*.ser")) {
            for (Path p : ds) {
                try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(p))) {
                    Object obj = ois.readObject();
                    if (obj instanceof SessionData sd) {
                        list.add(sd);
                    }
                } catch (Exception ignored) {}
            }
        } catch (IOException ignored) {}
        list.sort(Comparator.comparingLong(s -> -s.createdAt));
        return list;
    }

    private void deleteSession(SessionData data) {
        try {
            Files.deleteIfExists(sessionPath(data.id));
            Files.deleteIfExists(REPORT_DIR.resolve("session-" + data.id + ".json"));
        } catch (IOException ignored) {}
        writePortalPage();
    }

    private String categoryOf(Question q) {
        String pkg = q.getClass().getPackageName();
        if (!pkg.startsWith(QUESTION_BASE_PACKAGE)) {
            return "other";
        }
        String remainder = pkg.substring(QUESTION_BASE_PACKAGE.length());
        if (remainder.startsWith(".")) {
            remainder = remainder.substring(1);
        }
        if (remainder.isEmpty()) {
            return "core";
        }
        int dot = remainder.indexOf('.');
        String firstSegment = (dot >= 0) ? remainder.substring(0, dot) : remainder;
        return firstSegment.toLowerCase();
    }

    // ── SESSION RUNNER ──────────────────────────────────────────────

    private void runSession(SessionData data) {
        List<Question> qs = questionsFromSession(data);
        if (qs.isEmpty()) {
            System.out.println("  No questions available in this session.");
            return;
        }
        ensureShuffled(data, qs);

        int total = qs.size();
        List<MissedQuestion> missed = new ArrayList<>();
        // add prior misses if resuming
        for (int i = 0; i < data.cursor && i < total; i++) {
            int ans = data.userAnswers.get(i);
            if (ans >= 0 && ans != data.shuffledCorrect.get(i)) {
                Question q = qs.get(i);
                List<String> disp = data.shuffledChoices.get(i);
                String userAnswer = toLetter(ans) + ") " + disp.get(ans);
                String correctAns = toLetter(data.shuffledCorrect.get(i)) + ") " + disp.get(data.shuffledCorrect.get(i));
                missed.add(new MissedQuestion(i + 1, q, userAnswer, correctAns));
            }
        }

        for (int i = data.cursor; i < total; i++) {
            clearScreen();
            Question q = qs.get(i);
            List<String> dispChoices = data.shuffledChoices.get(i);
            int correctShuffled = data.shuffledCorrect.get(i);
            ShuffledChoices shuffled = new ShuffledChoices(dispChoices, correctShuffled);

            int answeredSoFar = i;
            int correctSoFar = countCorrect(data, i);
            printQuestionHeader(data.mode.toUpperCase(), i, total, correctSoFar, q);
            String text = (i < data.questionTexts.size()) ? data.questionTexts.get(i) : q.getQuestionText();
            System.out.println(DIM + text + RESET);
            printChoices(shuffled.displayChoices());

            int selectedIndex = readAnswerIndex(shuffled.displayChoices().size());
            if (selectedIndex < 0) {
                System.out.println("\nInput closed. Exiting.");
                break;
            }
            int correctIndex = shuffled.shuffledCorrectIndex();

            data.userAnswers.set(i, selectedIndex);
            data.cursor = i + 1;
            saveSession(data);

            if (data.mode.equals("study")) {
                int correctSoFarNow = countCorrect(data, i + 1);
                if (selectedIndex == correctIndex) {
                    System.out.println("\n  " + GREEN + ">>> Correct!" + RESET);
                    double pctSoFar = correctSoFarNow * 100.0 / (i + 1);
                    System.out.printf("  Running score: %d/%d correct so far (%.1f%%)%n", correctSoFarNow, i + 1, pctSoFar);
                } else {
                    System.out.println("\n  " + RED + ">>> Incorrect." + RESET);
                    System.out.println("  The correct answer was: "
                            + toLetter(correctIndex) + ") "
                            + shuffled.displayChoices().get(correctIndex));
                    double pctSoFar = correctSoFarNow * 100.0 / (i + 1);
                    System.out.printf("  Running score: %d/%d correct so far (%.1f%%)%n", correctSoFarNow, i + 1, pctSoFar);
                    System.out.println();
                    System.out.println("  Explanation:");
                    System.out.println(q.getExplanation());
                    System.out.print("\n  Press Enter to continue...");
                    safeReadLine();
                }
            } else { // test mode
                if (selectedIndex != correctIndex) {
                    String userAnswer = toLetter(selectedIndex) + ") " + shuffled.displayChoices().get(selectedIndex);
                    String correctAns = toLetter(correctIndex) + ") " + shuffled.displayChoices().get(correctIndex);
                    missed.add(new MissedQuestion(i + 1, q, userAnswer, correctAns));
                } else {
                    correctSoFar++;
                }
            }
        }

        if (data.cursor >= total) {
            data.completed = true;
            saveSession(data);
        }

        if (data.mode.equals("test") && data.cursor >= total) {
            int correct = countCorrect(data, total);
            printGradeReport(correct, total, missed);
        }
    }

    private int countCorrect(SessionData data, int upTo) {
        int count = 0;
        for (int i = 0; i < upTo && i < data.userAnswers.size(); i++) {
            int ans = data.userAnswers.get(i);
            if (ans >= 0 && i < data.shuffledCorrect.size() && ans == data.shuffledCorrect.get(i)) count++;
        }
        return count;
    }

    private List<Question> questionsFromSession(SessionData data) {
        List<Question> list = new ArrayList<>();
        for (String name : data.classNames) {
            Question q = questionByName.get(name);
            if (q == null) {
                try {
                    q = (Question) Class.forName(name).getDeclaredConstructor().newInstance();
                    questionByName.put(name, q);
                } catch (Exception ignored) {}
            }
            if (q != null) list.add(q);
        }
        return list;
    }

    private void ensureShuffled(SessionData data, List<Question> qs) {
        if (data.shuffledChoices != null && data.shuffledChoices.size() == qs.size()) {
            return;
        }
        data.shuffledChoices = new ArrayList<>();
        data.shuffledCorrect = new ArrayList<>();
        for (Question q : qs) {
            ShuffledChoices sc = shuffleChoices(q);
            data.shuffledChoices.add(new ArrayList<>(sc.displayChoices()));
            data.shuffledCorrect.add(sc.shuffledCorrectIndex());
        }
    }

    // Portal page generation ---------------------------------------------------

    private void writePortalPage() {
        try {
            List<SessionData> sessions = loadSessions();
            Files.createDirectories(REPORT_DIR);
            for (SessionData s : sessions) {
                writeSessionJson(s);
            }
            writeSessionsIndexJson(sessions);
            writePortalHtmlSkeleton();
        } catch (Exception e) {
            System.err.println("Warning: could not write portal page: " + e.getMessage());
        }
    }

    private void writeSessionJson(SessionData s) throws IOException {
        int total = s.classNames.size();
        int correct = countCorrect(s, total);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        field(sb, "id", s.id); sb.append(",");
        field(sb, "mode", s.mode); sb.append(",");
        field(sb, "focus", s.focus); sb.append(",");
        field(sb, "subfocus", s.subfocus); sb.append(",");
        field(sb, "createdAt", s.createdAt); sb.append(",");
        field(sb, "completed", s.completed); sb.append(",");
        field(sb, "cursor", s.cursor); sb.append(",");
        field(sb, "total", total); sb.append(",");
        field(sb, "correct", correct); sb.append(",");
        sb.append("\"questions\":[");
        for (int q = 0; q < total; q++) {
            if (q > 0) sb.append(",");
            sb.append("{");
            field(sb, "idx", q + 1); sb.append(",");
            String cls = s.classNames.get(q);
            String category = categoryFromClassName(cls);
            field(sb, "category", category); sb.append(",");
            field(sb, "className", cls); sb.append(",");
            field(sb, "text", s.questionTexts.get(q)); sb.append(",");
            sb.append("\"choices\":[");
            List<String> disp = (s.shuffledChoices != null && q < s.shuffledChoices.size()) ? s.shuffledChoices.get(q) : s.choices.get(q);
            for (int c = 0; c < disp.size(); c++) {
                if (c > 0) sb.append(",");
                quote(sb, disp.get(c));
            }
            sb.append("],");
            int correctIdx = (s.shuffledCorrect != null && q < s.shuffledCorrect.size()) ? s.shuffledCorrect.get(q) : s.correctIndices.get(q);
            field(sb, "correctIndex", correctIdx); sb.append(",");
            int ans = (q < s.userAnswers.size()) ? s.userAnswers.get(q) : -1;
            field(sb, "userAnswer", ans);
            sb.append("}");
        }
        sb.append("]}");
        Files.writeString(REPORT_DIR.resolve("session-" + s.id + ".json"), sb.toString());
    }

    private void writeSessionsIndexJson(List<SessionData> sessions) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < sessions.size(); i++) {
            SessionData s = sessions.get(i);
            int total = s.classNames.size();
            int correct = countCorrect(s, total);
            if (i > 0) sb.append(",");
            sb.append("{");
            field(sb, "id", s.id); sb.append(",");
            field(sb, "mode", s.mode); sb.append(",");
            field(sb, "focus", s.focus); sb.append(",");
            field(sb, "subfocus", s.subfocus); sb.append(",");
            field(sb, "createdAt", s.createdAt); sb.append(",");
            field(sb, "completed", s.completed); sb.append(",");
            field(sb, "cursor", s.cursor); sb.append(",");
            field(sb, "total", total); sb.append(",");
            field(sb, "correct", correct); sb.append(",");
            field(sb, "file", "session-" + s.id + ".json");
            sb.append("}");
        }
        sb.append("]");
        Files.writeString(REPORT_DIR.resolve("sessions-index.json"), sb.toString());
    }

    private void writePortalHtmlSkeleton() throws IOException {
        String html = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Quiz Sessions</title>"
                + "<style>body{font-family:Arial, sans-serif;margin:0;background:#f7f7f7;color:#222;}header{background:#1f2937;color:#fff;padding:12px 16px;}main{display:flex;height:calc(100vh - 52px);}aside{width:320px;overflow:auto;border-right:1px solid #ccc;background:#fff;}section{flex:1;overflow:auto;padding:16px;}ul{list-style:none;padding:0;margin:0;}li{padding:10px 12px;border-bottom:1px solid #eee;cursor:pointer;}li:hover{background:#f0f0f0;}li.active{background:#e0f2ff;}table{border-collapse:collapse;width:100%;margin-top:12px;}th,td{border:1px solid #ccc;padding:6px 8px;vertical-align:top;}th{background:#ececec;}tr:nth-child(even){background:#fcfcfc;}code{white-space:pre-wrap;display:block;} .ok{color:#0a0;} .bad{color:#c00;} .miss{color:#999;}</style>"
                + "</head><body><header><h2>Quiz Sessions</h2></header><main>"
                + "<aside><ul id='sessionList'></ul></aside>"
                + "<section><div id='details'>Loading sessions…</div></section>"
                + "<script>"
                + "async function loadIndex(){const res=await fetch('sessions-index.json');if(!res.ok){details.innerHTML='No sessions.';return;}const sessions=await res.json();renderList(sessions);} "
                + "const ul=document.getElementById('sessionList');const details=document.getElementById('details');"
                + "async function renderList(sessions){ul.innerHTML='';sessions.forEach((s,i)=>{const li=document.createElement('li');const pct=s.total?((s.correct*100)/s.total).toFixed(1):'0.0';li.textContent=`${s.mode.toUpperCase()} | ${s.focus}/${s.subfocus} | ${s.completed?'done':'in-progress'} | ${pct}% | ${new Date(s.createdAt).toLocaleString()}`;li.onclick=()=>select(s,i);li.id='sess-'+i;ul.appendChild(li);}); if(sessions.length>0) select(sessions[0],0);} "
                + "async function select(sess,i){document.querySelectorAll('#sessionList li').forEach(e=>e.classList.remove('active'));const li=document.getElementById('sess-'+i);if(li)li.classList.add('active');const res=await fetch(sess.file);if(!res.ok){details.innerHTML='Could not load session file.';return;}const s=await res.json();let html=`<h3>${s.mode.toUpperCase()} – ${s.focus}/${s.subfocus}</h3>`;const pct=s.total?((s.correct*100)/s.total).toFixed(1):'0.0';html+=`<p><b>ID:</b> ${s.id} &nbsp; <b>Created:</b> ${new Date(s.createdAt).toLocaleString()} &nbsp; <b>Status:</b> ${s.completed?'Completed':'In-progress'}<br><b>Score:</b> ${s.correct}/${s.total} (${pct}%)</p>`;html+=\"<table><tr><th>#</th><th>Category</th><th>Question</th><th>Choices</th><th>Correct</th><th>Your Answer</th><th>Status</th></tr>\";s.questions.forEach(q=>{const correct=q.correctIndex;const ua=q.userAnswer;const status=ua<0?'<span class=miss>Unanswered</span>':(ua===correct?'<span class=ok>Correct</span>':'<span class=bad>Incorrect</span>');html+=`<tr><td>${q.idx}</td><td>${q.category}</td><td><code>${escapeHtml(q.text)}</code></td><td>`;q.choices.forEach((c,j)=>{html+=`<div>${String.fromCharCode(65+j)}) ${escapeHtml(c)}</div>`;});html+=`</td><td>${String.fromCharCode(65+correct)}) ${escapeHtml(q.choices[correct])}</td>`;if(ua>=0&&ua<q.choices.length){html+=`<td>${String.fromCharCode(65+ua)}) ${escapeHtml(q.choices[ua])}</td>`;}else{html+='<td>Unanswered</td>'; } html+=`<td>${status}</td></tr>`;});html+='</table>';details.innerHTML=html;} "
                + "function escapeHtml(s){return s.replace(/[&<>]/g,ch=>({'&':'&amp;','<':'&lt;','>':'&gt;'}[ch]));}"
                + "loadIndex();"
                + "</script></main></body></html>";
        Files.writeString(REPORT_DIR.resolve("index.html"), html);
    }

    private void field(StringBuilder sb, String key, String val) {
        quote(sb, key); sb.append(":"); quote(sb, val == null ? "" : val);
    }
    private void field(StringBuilder sb, String key, long val) { quote(sb, key); sb.append(":").append(val); }
    private void field(StringBuilder sb, String key, boolean val) { quote(sb, key); sb.append(":").append(val); }
    private void field(StringBuilder sb, String key, int val) { quote(sb, key); sb.append(":").append(val); }
    private void quote(StringBuilder sb, String s) {
        sb.append("\"");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\' -> sb.append("\\\\");
                case '"' -> sb.append("\\\"");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                case '\f' -> sb.append("\\f");
                default -> sb.append(c);
            }
        }
        sb.append("\"");
    }

    private String categoryFromClassName(String className) {
        if (className == null || !className.startsWith(QUESTION_BASE_PACKAGE)) return "OTHER";
        String remainder = className.substring(QUESTION_BASE_PACKAGE.length());
        if (remainder.startsWith(".")) remainder = remainder.substring(1);
        if (remainder.isEmpty()) return "CORE";
        String[] parts = remainder.split("\\.");
        if (parts.length >= 2) return (parts[0] + "/" + parts[1]).toUpperCase();
        return parts[0].toUpperCase();
    }

    // ── TEST MODE ───────────────────────────────────────────────────

    private void runTestMode(List<Question> selected) {
        if (selected.isEmpty()) {
            System.out.println("  No questions available for that selection.");
            return;
        }

        int total = selected.size();
        int correct = 0;

        List<MissedQuestion> missed = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            clearScreen();
            Question q = selected.get(i);
            ShuffledChoices shuffled = shuffleChoices(q);

            printQuestionHeader("TEST", i, total, correct, q);
            System.out.println(DIM + q.getQuestionText() + RESET);
            printChoices(shuffled.displayChoices());

            int selectedIndex = readAnswerIndex(shuffled.displayChoices().size());
            if (selectedIndex < 0) {
                System.out.println("\nInput closed. Exiting.");
                break;
            }
            int correctIndex = shuffled.shuffledCorrectIndex();

            if (selectedIndex == correctIndex) {
                correct++;
            } else {
                String userAnswer = toLetter(selectedIndex) + ") " + shuffled.displayChoices().get(selectedIndex);
                String correctAns = toLetter(correctIndex) + ") " + shuffled.displayChoices().get(correctIndex);
                missed.add(new MissedQuestion(i + 1, q, userAnswer, correctAns));
            }
        }

        // ── Grade Report ──
        printGradeReport(correct, total, missed);
    }

    private void printGradeReport(int correct, int total, List<MissedQuestion> missed) {
        double pct = (total == 0) ? 0.0 : (correct * 100.0) / total;

        System.out.println("\n" + SEPARATOR);
        System.out.println("  RESULTS");
        System.out.println(SEPARATOR);
        System.out.printf("  Score: %d / %d  (%.1f%%)%n", correct, total, pct);
        System.out.println(THIN_SEPARATOR);

        if (missed.isEmpty()) {
            System.out.println(GREEN + "  Perfect score! Well done." + RESET);
        } else {
            System.out.println("  Missed questions:");
            System.out.println();
            for (var m : missed) {
                System.out.printf("  #%d%n", m.displayNum());
                System.out.println("    Your answer:    " + m.userAnswer());
                System.out.println("    Correct answer: " + m.correctAnswer());
                System.out.println();
            }

            System.out.println(THIN_SEPARATOR);
            System.out.print("  Show detailed explanations for missed questions? (y/n): ");
            String choiceRaw = safeReadLine();
            if (choiceRaw == null) {
                System.out.println("\nInput closed. Exiting.");
                return;
            }
            String choice = choiceRaw.trim().toLowerCase();
            if (choice.equals("y") || choice.equals("yes")) {
                for (var m : missed) {
                    System.out.println("\n" + THIN_SEPARATOR);
                    System.out.printf("  Explanation for #%d:%n", m.displayNum());
                    System.out.println(THIN_SEPARATOR);
                    System.out.println(m.question().getExplanation());
                }
            }
        }
        System.out.println(SEPARATOR);
    }

    // ── STUDY MODE ──────────────────────────────────────────────────

    private void runStudyMode(List<Question> selected) {
        if (selected.isEmpty()) {
            System.out.println("  No questions available for that selection.");
            return;
        }

        int total = selected.size();
        int correct = 0;

        for (int i = 0; i < total; i++) {
            clearScreen();
            Question q = selected.get(i);
            ShuffledChoices shuffled = shuffleChoices(q);

            printQuestionHeader("STUDY", i, total, correct, q);
            System.out.println(DIM + q.getQuestionText() + RESET);
            printChoices(shuffled.displayChoices());

            int selectedIndex = readAnswerIndex(shuffled.displayChoices().size());
            if (selectedIndex < 0) {
                System.out.println("\nInput closed. Exiting.");
                break;
            }
            int correctIndex = shuffled.shuffledCorrectIndex();

            if (selectedIndex == correctIndex) {
                correct++;
                System.out.println("\n  " + GREEN + ">>> Correct!" + RESET);
                double pctSoFar = correct * 100.0 / (i + 1);
                System.out.printf("  Running score: %d/%d correct so far (%.1f%%)%n", correct, i + 1, pctSoFar);
                //System.out.printf("  Running score: %d/%d correct so far%n", correct, i + 1);
//                System.out.print("  Show explanation anyway? (y/n): ");
//                String show = scanner.nextLine().trim().toLowerCase();
//                if (show.equals("y") || show.equals("yes")) {
//                    System.out.println();
//                    System.out.println(q.getExplanation());
//                }
            } else {
                System.out.println("\n  " + RED + ">>> Incorrect." + RESET);
                System.out.println("  The correct answer was: "
                        + toLetter(correctIndex) + ") "
                        + shuffled.displayChoices().get(correctIndex));
                double pctSoFar = correct * 100.0 / (i + 1);
                System.out.printf("  Running score: %d/%d correct so far (%.1f%%)%n", correct, i + 1, pctSoFar);
                System.out.println();
                System.out.println("  Explanation:");
                System.out.println(q.getExplanation());
                System.out.print("\n  Press Enter to continue...");
                safeReadLine();
            }

        }

        // ── Final Score ──
        double pct = (total == 0) ? 0.0 : (correct * 100.0) / total;
        System.out.println("\n" + SEPARATOR);
        System.out.println("  STUDY SESSION COMPLETE");
        System.out.println(SEPARATOR);
        System.out.printf("  Final score: %d / %d  (%.1f%%)%n", correct, total, pct);
        System.out.println(SEPARATOR);
    }

    // ── CHOICE SHUFFLING ────────────────────────────────────────────

    private record ShuffledChoices(List<String> displayChoices, int shuffledCorrectIndex) {}

    private ShuffledChoices shuffleChoices(Question question) {
        List<String> original = question.getChoices();
        int correctOriginal = question.getCorrectAnswerIndex();
        int size = original.size();

        // Build index list; optionally pin the last element
        List<Integer> indices;
        if (question.hasFixedLastChoice() && size > 1) {
            // Shuffle all indices except the last one
            indices = new ArrayList<>(IntStream.range(0, size - 1).boxed().toList());
            Collections.shuffle(indices);
            indices.add(size - 1); // pin last
        } else {
            indices = new ArrayList<>(IntStream.range(0, size).boxed().toList());
            Collections.shuffle(indices);
        }

        // Build display list and find the new position of the correct answer
        List<String> display = new ArrayList<>(size);
        int shuffledCorrect = -1;
        for (int newPos = 0; newPos < indices.size(); newPos++) {
            int origPos = indices.get(newPos);
            display.add(original.get(origPos));
            if (origPos == correctOriginal) {
                shuffledCorrect = newPos;
            }
        }

        return new ShuffledChoices(Collections.unmodifiableList(display), shuffledCorrect);
    }

    // ── INPUT / OUTPUT HELPERS ──────────────────────────────────────

    private void printChoices(List<String> choices) {
        for (int i = 0; i < choices.size(); i++) {
            System.out.printf("    %s%s)%s %s%n", YELLOW, toLetter(i), RESET, choices.get(i));
        }
        System.out.println();
    }

    /**
     * Reads a valid answer letter from the user, re-prompting on invalid input.
     * Returns the zero-based index (A=0, B=1, ...).
     */
    private int readAnswerIndex(int numChoices) {
        String maxLetter = toLetter(numChoices - 1);
        while (true) {
            System.out.print("  Your answer (A-" + maxLetter + "): ");
            String line = safeReadLine();
            if (line == null) {
                return -1;
            }
            String raw = line.trim().toUpperCase();
            if (raw.length() == 1) {
                int idx = raw.charAt(0) - 'A';
                if (idx >= 0 && idx < numChoices) {
                    return idx;
                }
            }
            System.out.println("  Invalid input. Please enter a letter between A and " + maxLetter + ".");
        }
    }

    private static String toLetter(int index) {
        return String.valueOf((char) ('A' + index));
    }

    private String categoryLabel(Question q) {
        String[] parts = packageSegments(q);
        if (parts.length == 0) return "GENERAL";
        if (parts.length == 1) return parts[0].toUpperCase();
        return (parts[0] + "/" + parts[1]).toUpperCase();
    }

    private void clearScreen() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    private void printQuestionHeader(String mode, int answeredSoFar, int total, int correctSoFar, Question q) {
        double accuracy = (answeredSoFar == 0) ? 0.0 : (correctSoFar * 100.0 / answeredSoFar);
        double progress = (total == 0) ? 0.0 : (answeredSoFar * 100.0 / total);
        System.out.println(CYAN + SEPARATOR + RESET);
        System.out.printf("%s  %s MODE%s | Question %d of %d | Progress: %.1f%% | Correct: %d/%d (%.1f%%)%n",
                CYAN, mode, RESET, answeredSoFar + 1, total, progress, correctSoFar, answeredSoFar, accuracy);
        System.out.printf("  Category: %s%s%s%n", BLUE, categoryLabel(q), RESET);
        System.out.println(CYAN + SEPARATOR + RESET);
        System.out.println();
    }

    private String safeReadLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            return null;
        }
    }

    private int selectFromList(List<String> options) {
        while (true) {
            System.out.println(CYAN + SEPARATOR + RESET);
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("  %d) %s%n", i + 1, options.get(i));
            }
            System.out.print("\nChoose 1-" + options.size() + " (Enter to cancel): ");
            String line = safeReadLine();
            if (line == null || line.trim().isEmpty()) {
                return options.size() - 1; // default last item (quit/cancel)
            }
            try {
                int n = Integer.parseInt(line.trim());
                if (n >= 1 && n <= options.size()) return n - 1;
            } catch (NumberFormatException ignored) {}
            System.out.println("  Invalid choice, press Enter to retry.");
            safeReadLine();
        }
    }

    private void printBanner() {
        System.out.println(SEPARATOR);
        System.out.println();
        System.out.println("    Gotham Asset Management  --  Interview Prep Quiz");
        System.out.println("    " + questions.size() + " questions loaded");
        System.out.println();
        System.out.println(SEPARATOR);
    }
}
