package gotham.asset.mgmt.multiple.choice;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.IntStream;

/**
 * REPL-style console quiz application with "test" and "study" modes.
 * Dynamically discovers all Question subclasses via classpath scanning.
 * Shuffles question order and answer order each run.
 */
public class QuizApp {

    private static final String SEPARATOR = "=".repeat(60);
    private static final String THIN_SEPARATOR = "-".repeat(60);
    private static final String QUESTION_BASE_PACKAGE = "gotham.asset.mgmt.multiple.choice.questions";

    private record MissedQuestion(int displayNum, Question question, String userAnswer, String correctAnswer) {}

    private final Scanner scanner;
    private final List<Question> questions;

    public QuizApp(Scanner scanner) {
        this.scanner = scanner;
        this.questions = new ArrayList<>(discoverQuestions());
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
        app.run();
        scanner.close();
    }

    // ── REPL ────────────────────────────────────────────────────────

    private void run() {
        printBanner();
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("  Choose a mode:");
            System.out.println("    test   - Answer all questions, see your grade at the end");
            System.out.println("    study  - Answer with immediate feedback after each question");
            System.out.println("    quit   - Exit the application");
            System.out.println(SEPARATOR);
            System.out.print("> ");

            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "test"  -> runTestMode(selectQuestions());
                case "study" -> runStudyMode(selectQuestions());
                case "quit", "exit", "q" -> {
                    System.out.println("\nGoodbye! Happy studying.");
                    running = false;
                }
                default -> System.out.println("  Invalid option. Please type 'test', 'study', or 'quit'.");
            }
        }
    }

    private List<Question> selectQuestions() {
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
            return distributeEvenly(byTop.values());
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
        } else {
            pool = byTop.getOrDefault(focus, List.of());
        }

        if (pool.isEmpty()) {
            System.out.println("  No questions found for that selection.");
            return List.of();
        }

        return pickCount(pool);
    }

    private List<Question> pickCount(List<Question> pool) {
        int count = promptCount(pool.size());
        List<Question> copy = new ArrayList<>(pool);
        Collections.shuffle(copy);
        return new ArrayList<>(copy.subList(0, count));
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
        System.out.println("\n  " + label + "? (" + String.join(" / ", opts) + " / all)");
        System.out.print("  > ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.isEmpty()) return "all";
        if (input.equals("all") || options.contains(input)) return input;
        System.out.println("  Unknown choice '" + input + "', using 'all'.");
        return "all";
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
        String raw = scanner.nextLine().trim();
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

    // ── TEST MODE ───────────────────────────────────────────────────

    private void runTestMode(List<Question> selected) {
        if (selected.isEmpty()) {
            System.out.println("  No questions available for that selection.");
            return;
        }

        Collections.shuffle(selected);
        int total = selected.size();
        int correct = 0;

        List<MissedQuestion> missed = new ArrayList<>();

        System.out.println("\n" + SEPARATOR);
        System.out.println("  TEST MODE  --  " + total + " questions");
        System.out.println("  Answer all questions. Results shown at the end.");
        System.out.println(SEPARATOR);

        for (int i = 0; i < total; i++) {
            Question q = selected.get(i);
            ShuffledChoices shuffled = shuffleChoices(q);

            System.out.println("\n" + SEPARATOR);
            System.out.printf("  Question %d of %d%n", i + 1, total);
            System.out.println(SEPARATOR);
            System.out.println();
            System.out.println(q.getQuestionText());
            printChoices(shuffled.displayChoices());

            int selectedIndex = readAnswerIndex(shuffled.displayChoices().size());
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
            System.out.println("  Perfect score! Well done.");
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
            String choice = scanner.nextLine().trim().toLowerCase();
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

        Collections.shuffle(selected);
        int total = selected.size();
        int correct = 0;

        System.out.println("\n" + SEPARATOR);
        System.out.println("  STUDY MODE  --  " + total + " questions");
        System.out.println("  You will receive feedback after each question.");
        System.out.println(SEPARATOR);

        for (int i = 0; i < total; i++) {
            Question q = selected.get(i);
            ShuffledChoices shuffled = shuffleChoices(q);

            System.out.println("\n" + SEPARATOR);
            System.out.printf("Question %d of %d\t[%s]%n", i + 1, total, categoryLabel(q));
            System.out.println(SEPARATOR);
            System.out.println();
            System.out.println(q.getQuestionText());
            printChoices(shuffled.displayChoices());

            int selectedIndex = readAnswerIndex(shuffled.displayChoices().size());
            int correctIndex = shuffled.shuffledCorrectIndex();

            if (selectedIndex == correctIndex) {
                correct++;
                System.out.println("\n  >>> Correct!");
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
                System.out.println("\n  >>> Incorrect.");
                System.out.println("  The correct answer was: "
                        + toLetter(correctIndex) + ") "
                        + shuffled.displayChoices().get(correctIndex));
                double pctSoFar = correct * 100.0 / (i + 1);
                System.out.printf("  Running score: %d/%d correct so far (%.1f%%)%n", correct, i + 1, pctSoFar);
                System.out.println();
                System.out.println("  Explanation:");
                System.out.println(q.getExplanation());
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
            System.out.printf("    %s) %s%n", toLetter(i), choices.get(i));
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
            String raw = scanner.nextLine().trim().toUpperCase();
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

    private void printBanner() {
        System.out.println(SEPARATOR);
        System.out.println();
        System.out.println("    Gotham Asset Management  --  Interview Prep Quiz");
        System.out.println("    " + questions.size() + " questions loaded");
        System.out.println();
        System.out.println(SEPARATOR);
    }
}
