package gotham.asset.mgmt.multiple.choice;

import gotham.asset.mgmt.multiple.choice.engine.QuizEngine;
import gotham.asset.mgmt.multiple.choice.io.ConsoleIO;
import gotham.asset.mgmt.multiple.choice.io.QuizIO;
import gotham.asset.mgmt.multiple.choice.questions.ClassroomQuestionProvider;
import gotham.asset.mgmt.multiple.choice.questions.QuestionProvider;
import gotham.asset.mgmt.multiple.choice.repo.FileSessionRepository;
import gotham.asset.mgmt.multiple.choice.repo.SessionRepository;
import gotham.asset.mgmt.multiple.choice.report.DiskReportExporter;
import gotham.asset.mgmt.multiple.choice.report.ReportExporter;
import gotham.asset.mgmt.multiple.choice.selection.QuestionSelector;
import gotham.asset.mgmt.multiple.choice.ui.Menu;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Thin entrypoint that wires the quiz components together.
 */
public class QuizApp {
    private static final Path SESSIONS_DIR = Paths.get(System.getProperty("user.home"), ".quiz-sessions");
    private static final Path REPORT_DIR = Paths.get("testReports");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizIO io = new ConsoleIO(scanner);
        QuestionProvider provider = new ClassroomQuestionProvider();
        SessionRepository repo = new FileSessionRepository(SESSIONS_DIR);
        ReportExporter exporter = new DiskReportExporter(REPORT_DIR);
        Menu menu = new Menu(io);
        QuestionSelector selector = new QuestionSelector();
        QuizEngine engine = new QuizEngine(io, repo, exporter);
        AppController controller = new AppController(io, provider, selector, menu, engine, repo, exporter);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> io.println("\nInterrupted, exiting.")));
        controller.run();
    }
}
