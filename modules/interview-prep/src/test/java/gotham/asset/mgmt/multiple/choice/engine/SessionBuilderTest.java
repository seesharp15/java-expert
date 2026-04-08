package gotham.asset.mgmt.multiple.choice.engine;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import gotham.asset.mgmt.multiple.choice.repo.SessionData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class SessionBuilderTest {

    @Test
    void keepsFixedLastChoiceAtEnd() {
        Question fixed = new FixedLastQuestion();
        SessionData data = SessionBuilder.build("test", "java", "core", List.of(fixed), new Random(42));

        List<String> disp = data.shuffledChoices.get(0);
        assertThat(disp.get(disp.size() - 1)).isEqualTo("All of the above");
        int correctIdx = data.shuffledCorrect.get(0);
        assertThat(disp.get(correctIdx)).isEqualTo("All of the above");
    }

    @Test
    void deDuplicatesByClassName() {
        Question fixed = new FixedLastQuestion();
        SessionData data = SessionBuilder.build("test", "java", "core", List.of(fixed, fixed), new Random(1));
        assertThat(data.classNames).hasSize(1);
    }

    private static class FixedLastQuestion extends Question {
        @Override public String getQuestionText() { return "Pick all"; }
        @Override public List<String> getChoices() { return List.of("A", "B", "All of the above"); }
        @Override public int getCorrectAnswerIndex() { return 2; }
        @Override public String getExplanation() { return "Last choice should stay last."; }
        @Override public boolean hasFixedLastChoice() { return true; }
    }
}
