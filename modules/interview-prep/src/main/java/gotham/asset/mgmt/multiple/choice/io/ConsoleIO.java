package gotham.asset.mgmt.multiple.choice.io;

import gotham.asset.mgmt.multiple.choice.util.Colors;

import java.util.Scanner;

public class ConsoleIO implements QuizIO {
    private final Scanner scanner;

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public void clearScreen() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    public void banner(String text, int width) {
        String sep = "=".repeat(width);
        println(Colors.CYAN + sep + Colors.RESET);
        println(text);
        println(Colors.CYAN + sep + Colors.RESET);
    }
}
