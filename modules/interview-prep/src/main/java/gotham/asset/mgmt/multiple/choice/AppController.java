package gotham.asset.mgmt.multiple.choice;

import gotham.asset.mgmt.multiple.choice.engine.QuizEngine;
import gotham.asset.mgmt.multiple.choice.engine.SessionBuilder;
import gotham.asset.mgmt.multiple.choice.io.QuizIO;
import gotham.asset.mgmt.multiple.choice.questions.Question;
import gotham.asset.mgmt.multiple.choice.questions.QuestionProvider;
import gotham.asset.mgmt.multiple.choice.repo.SessionData;
import gotham.asset.mgmt.multiple.choice.repo.SessionMeta;
import gotham.asset.mgmt.multiple.choice.repo.SessionRepository;
import gotham.asset.mgmt.multiple.choice.report.ReportExporter;
import gotham.asset.mgmt.multiple.choice.selection.QuestionSelector;
import gotham.asset.mgmt.multiple.choice.ui.Menu;
import gotham.asset.mgmt.multiple.choice.util.Colors;

import java.util.*;

/**
 * Wires IO, question discovery, persistence, and quiz engine together.
 */
public class AppController {
    private final QuizIO io;
    private final QuestionProvider provider;
    private final QuestionSelector selector;
    private final Menu menu;
    private final QuizEngine engine;
    private final SessionRepository repo;
    private final ReportExporter exporter;
    private final Map<String, Question> questionByName;
    private final List<Question> allQuestions;

    public AppController(QuizIO io,
                         QuestionProvider provider,
                         QuestionSelector selector,
                         Menu menu,
                         QuizEngine engine,
                         SessionRepository repo,
                         ReportExporter exporter) {
        this.io = io;
        this.provider = provider;
        this.selector = selector;
        this.menu = menu;
        this.engine = engine;
        this.repo = repo;
        this.exporter = exporter;

        this.allQuestions = new ArrayList<>(new LinkedHashSet<>(provider.loadAll()));
        this.questionByName = new HashMap<>();
        for (Question q : allQuestions) {
            questionByName.put(q.getClass().getName(), q);
        }
    }

    public void run() {
        io.clearScreen();
        banner("Gotham Quiz");
        boolean running = true;
        while (running) {
            int choice = menu.select("Select an option:",
                    List.of("Test (new)", "Study (new)", "Load previous session", "Delete a saved session", "Quit"),
                    0);
            switch (choice) {
                case 0 -> startNew("test");
                case 1 -> startNew("study");
                case 2 -> loadAndRun();
                case 3 -> deleteSession();
                default -> running = false;
            }
        }
        io.println("\nGoodbye!");
    }

    private void startNew(String mode) {
        QuestionSelector.Selection selection = selector.select(allQuestions, io, menu);
        if (selection.questions().isEmpty()) {
            io.println("No questions available for that selection.");
            return;
        }
        SessionData data = SessionBuilder.build(mode, selection.focus(), selection.subfocus(), selection.questions(), new Random());
        repo.save(data);
        exporter.exportAll(repo.list(), loadAllSessions());
        engine.run(data, questionsForSession(data));
    }

    private void loadAndRun() {
        List<SessionMeta> metas = repo.list();
        if (metas.isEmpty()) {
            io.println("No saved sessions found.");
            return;
        }
        List<String> labels = new ArrayList<>();
        for (SessionMeta m : metas) {
            String status = m.completed() ? "completed" : ("in-progress " + m.cursor() + "/" + m.total());
            labels.add(String.format("%s | %s/%s | %s | %s", m.mode().toUpperCase(), m.focus(), m.subfocus(), status, new Date(m.createdAt())));
        }
        int idx = menu.select("Select a saved session", labels, 0);
        SessionMeta chosen = metas.get(idx);
        repo.load(chosen.id()).ifPresent(data -> engine.run(data, questionsForSession(data)));
    }

    private void deleteSession() {
        List<SessionMeta> metas = repo.list();
        if (metas.isEmpty()) {
            io.println("No saved sessions to delete.");
            return;
        }
        List<String> labels = new ArrayList<>();
        for (SessionMeta m : metas) {
            String status = m.completed() ? "completed" : ("in-progress " + m.cursor() + "/" + m.total());
            labels.add(String.format("%s | %s/%s | %s | %s", m.mode().toUpperCase(), m.focus(), m.subfocus(), status, new Date(m.createdAt())));
        }
        int idx = menu.select("Delete which session?", labels, 0);
        SessionMeta chosen = metas.get(idx);
        repo.delete(chosen.id());
        exporter.exportAll(repo.list(), loadAllSessions());
        io.println("Deleted session " + chosen.id());
    }

    private List<Question> questionsForSession(SessionData data) {
        List<Question> out = new ArrayList<>();
        for (String name : data.classNames) {
            Question q = questionByName.get(name);
            if (q == null) {
                try {
                    q = (Question) Class.forName(name).getDeclaredConstructor().newInstance();
                    questionByName.put(name, q);
                } catch (Exception ignored) {
                }
            }
            if (q != null) {
                out.add(q);
            }
        }
        return out;
    }

    private List<SessionData> loadAllSessions() {
        List<SessionData> out = new ArrayList<>();
        for (SessionMeta meta : repo.list()) {
            repo.load(meta.id()).ifPresent(out::add);
        }
        return out;
    }

    private void banner(String title) {
        String line = "=".repeat(80);
        io.println(Colors.CYAN + line + Colors.RESET);
        io.println("  " + title);
        io.println(Colors.CYAN + line + Colors.RESET);
    }
}
