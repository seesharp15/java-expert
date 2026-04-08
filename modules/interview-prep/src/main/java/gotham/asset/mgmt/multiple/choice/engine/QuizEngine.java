package gotham.asset.mgmt.multiple.choice.engine;

import gotham.asset.mgmt.multiple.choice.io.QuizIO;
import gotham.asset.mgmt.multiple.choice.questions.Question;
import gotham.asset.mgmt.multiple.choice.repo.SessionData;
import gotham.asset.mgmt.multiple.choice.repo.SessionRepository;
import gotham.asset.mgmt.multiple.choice.report.ReportExporter;
import gotham.asset.mgmt.multiple.choice.util.Colors;

import java.util.ArrayList;
import java.util.List;

public class QuizEngine {
    private final QuizIO io;
    private final SessionRepository repo;
    private final ReportExporter exporter;
    private final String separator = "=".repeat(80);
    private final String thinSeparator = "-".repeat(80);

    public QuizEngine(QuizIO io, SessionRepository repo, ReportExporter exporter) {
        this.io = io;
        this.repo = repo;
        this.exporter = exporter;
    }

    public void run(SessionData data, List<Question> questions) {
        int total = questions.size();
        for (int i = data.cursor; i < total; i++) {
            Question q = questions.get(i);
            List<String> dispChoices = data.shuffledChoices.get(i);
            int correctIndex = data.shuffledCorrect.get(i);

            io.clearScreen();
            printHeader(data.mode.toUpperCase(), i, total, countCorrect(data, i), q);
            io.println(Colors.DIM + data.questionTexts.get(i) + Colors.RESET);
            printChoices(dispChoices);

            int sel = readAnswer(dispChoices.size());
            if (sel < 0) {
                io.println("\nInput closed. Exiting session.");
                repo.save(data);
                exporter.exportAll(repo.list(), loadAllSessions());
                return;
            }

            data.userAnswers.set(i, sel);
            data.cursor = i + 1;
            repo.save(data);
            exporter.exportAll(repo.list(), loadAllSessions());

            int answered = i + 1;
            int correctSoFarNow = countCorrect(data, answered);
            double pctSoFar = answered == 0 ? 0.0 : (correctSoFarNow * 100.0 / answered);

            if ("study".equalsIgnoreCase(data.mode)) {
                if (sel == correctIndex) {
                    io.println("\n  " + Colors.GREEN + ">>> Correct!" + Colors.RESET);
                } else {
                    io.println("\n  " + Colors.RED + ">>> Incorrect." + Colors.RESET);
                    io.println("  The correct answer was: " + letter(correctIndex) + ") " + dispChoices.get(correctIndex));
                    io.println("\n  Explanation:");
                    io.println(q.getExplanation());
                }
                io.println(String.format("  Running score: %d/%d correct so far (%.1f%%)", correctSoFarNow, answered, pctSoFar));
                io.println("\nPress Enter to continue...");
                io.readLine();
            } else {
                io.println(String.format("\n  Running score: %d/%d correct so far (%.1f%%)", correctSoFarNow, answered, pctSoFar));
            }
        }
        data.completed = true;
        repo.save(data);
        exporter.exportAll(repo.list(), loadAllSessions());

        if ("test".equalsIgnoreCase(data.mode)) {
            int correct = countCorrect(data, questions.size());
            printGradeReport(correct, total, data, questions);
        }
    }

    private List<SessionData> loadAllSessions() {
        List<SessionData> out = new ArrayList<>();
        for (var meta : repo.list()) {
            repo.load(meta.id()).ifPresent(out::add);
        }
        return out;
    }

    private void printHeader(String mode, int answeredIdx, int total, int correctSoFar, Question q) {
        double progress = total == 0 ? 0.0 : (answeredIdx * 100.0 / total);
        double acc = answeredIdx == 0 ? 0.0 : (correctSoFar * 100.0 / answeredIdx);
        io.println(Colors.CYAN + separator + Colors.RESET);
        io.println(String.format("  %s MODE | Question %d of %d | Progress: %.1f%% | Correct: %d/%d (%.1f%%)",
                mode, answeredIdx + 1, total, progress, correctSoFar, answeredIdx, acc));
        io.println("  Category: " + categoryLabel(q));
        io.println(Colors.CYAN + separator + Colors.RESET);
    }

    private void printChoices(List<String> choices) {
        for (int i = 0; i < choices.size(); i++) {
            io.println("    " + Colors.YELLOW + letter(i) + ")" + Colors.RESET + " " + choices.get(i));
        }
        io.println("");
    }

    private int readAnswer(int numChoices) {
        String maxLetter = letter(numChoices - 1);
        while (true) {
            io.print("  Your answer (A-" + maxLetter + "): ");
            String line = io.readLine();
            if (line == null) return -1;
            line = line.trim().toUpperCase();
            if (line.length() == 1) {
                int idx = line.charAt(0) - 'A';
                if (idx >= 0 && idx < numChoices) return idx;
            }
            io.println("  Invalid input.");
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

    private void printGradeReport(int correct, int total, SessionData data, List<Question> qs) {
        double pct = total == 0 ? 0.0 : (correct * 100.0 / total);
        io.println("\n" + separator);
        io.println("  RESULTS");
        io.println(separator);
        io.println(String.format("  Score: %d / %d  (%.1f%%)", correct, total, pct));
        io.println(thinSeparator);
        if (correct == total) {
            io.println(Colors.GREEN + "  Perfect score! Well done." + Colors.RESET);
            return;
        }
        for (int i = 0; i < total; i++) {
            int ans = data.userAnswers.get(i);
            int correctIdx = data.shuffledCorrect.get(i);
            if (ans >= 0 && ans != correctIdx) {
                List<String> disp = data.shuffledChoices.get(i);
                io.println("  #" + (i + 1));
                io.println("    Your answer:    " + letter(ans) + ") " + disp.get(ans));
                io.println("    Correct answer: " + letter(correctIdx) + ") " + disp.get(correctIdx));
                io.println("");
            }
        }
    }

    private String letter(int i) { return String.valueOf((char) ('A' + i)); }

    private String categoryLabel(Question q) {
        String pkg = q.getClass().getPackageName();
        String base = "gotham.asset.mgmt.multiple.choice.questions.";
        if (!pkg.startsWith(base)) return "GENERAL";
        String remainder = pkg.substring(base.length());
        if (remainder.isEmpty()) return "CORE";
        String[] parts = remainder.split("\\.");
        if (parts.length >= 2) return (parts[0] + "/" + parts[1]).toUpperCase();
        return parts[0].toUpperCase();
    }
}
