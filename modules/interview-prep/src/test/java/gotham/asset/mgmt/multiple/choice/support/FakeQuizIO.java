package gotham.asset.mgmt.multiple.choice.support;

import gotham.asset.mgmt.multiple.choice.io.QuizIO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Minimal QuizIO double for unit tests.
 */
public class FakeQuizIO implements QuizIO {
    private final Deque<String> inputs = new ArrayDeque<>();
    private final List<String> outputs = new ArrayList<>();

    public FakeQuizIO(List<String> scriptedInputs) {
        this.inputs.addAll(scriptedInputs);
    }

    @Override
    public String readLine() {
        return inputs.isEmpty() ? null : inputs.removeFirst();
    }

    @Override
    public void print(String s) {
        outputs.add(s);
    }

    @Override
    public void println(String s) {
        outputs.add(s);
    }

    @Override
    public void clearScreen() {
        outputs.add("[clear]");
    }

    public List<String> outputs() {
        return outputs;
    }
}
