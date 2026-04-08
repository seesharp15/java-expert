package gotham.asset.mgmt.multiple.choice.selection;

import gotham.asset.mgmt.multiple.choice.io.QuizIO;
import gotham.asset.mgmt.multiple.choice.questions.Question;
import gotham.asset.mgmt.multiple.choice.questions.SQL.DummySqlQuestion;
import gotham.asset.mgmt.multiple.choice.questions.dotnet.DummyDotnetQuestion;
import gotham.asset.mgmt.multiple.choice.questions.java.DummyJavaQuestion;
import gotham.asset.mgmt.multiple.choice.ui.Menu;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionSelectorTest {

    @Test
    void distributesEvenlyAcrossTopLevelCategories() {
        QuizIO io = new StubIO(List.of("1", "3")); // choose "all", then ask for 3 questions
        Menu menu = new Menu(io);
        QuestionSelector selector = new QuestionSelector();
        List<Question> all = List.of(new DummyJavaQuestion(), new DummySqlQuestion(), new DummyDotnetQuestion());

        QuestionSelector.Selection sel = selector.select(all, io, menu);

        assertThat(sel.questions()).hasSize(3);
        Set<String> categories = new HashSet<>();
        for (Question q : sel.questions()) {
            categories.add(topLevel(q));
        }
        assertThat(categories).containsExactlyInAnyOrder("java", "SQL", "dotnet");
    }

    private String topLevel(Question q) {
        String base = "gotham.asset.mgmt.multiple.choice.questions.";
        String pkg = q.getClass().getPackageName();
        String remainder = pkg.substring(base.length());
        int dot = remainder.indexOf('.');
        return (dot >= 0) ? remainder.substring(0, dot) : remainder;
    }

    private static class StubIO implements QuizIO {
        private final Iterator<String> inputs;
        StubIO(List<String> inputs) { this.inputs = inputs.iterator(); }
        @Override public String readLine() { return inputs.hasNext() ? inputs.next() : ""; }
        @Override public void print(String s) {}
        @Override public void println(String s) {}
        @Override public void clearScreen() {}
    }
}
