package gotham.asset.mgmt.multiple.choice.io;

public interface QuizIO {
    String readLine();
    void print(String s);
    void println(String s);
    default void printf(String fmt, Object... args) {
        print(String.format(fmt, args));
    }
    void clearScreen();
}
