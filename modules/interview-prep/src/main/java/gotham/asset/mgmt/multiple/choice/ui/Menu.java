package gotham.asset.mgmt.multiple.choice.ui;

import gotham.asset.mgmt.multiple.choice.io.QuizIO;
import gotham.asset.mgmt.multiple.choice.util.Colors;

import java.util.List;

public class Menu {
    private final QuizIO io;

    public Menu(QuizIO io) {
        this.io = io;
    }

    public int select(String title, List<String> options, int defaultIdx) {
        while (true) {
            io.println(Colors.CYAN + "------------------------------" + Colors.RESET);
            io.println(title);
            for (int i = 0; i < options.size(); i++) {
                io.println(String.format("  %d) %s", i + 1, options.get(i)));
            }
            io.print("Choose 1-" + options.size() + " (Enter=" + (defaultIdx + 1) + "): ");
            String line = io.readLine();
            if (line == null) return defaultIdx;
            line = line.trim();
            if (line.isEmpty()) return defaultIdx;
            try {
                int n = Integer.parseInt(line);
                if (n >= 1 && n <= options.size()) {
                    return n - 1;
                }
            } catch (NumberFormatException ignored) {}
            io.println("Invalid choice, try again.");
        }
    }
}
