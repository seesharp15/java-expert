package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.List;

public class DummyJavaQuestion extends Question {
    @Override
    public String getQuestionText() {
        return "Dummy Java question";
    }

    @Override
    public List<String> getChoices() {
        return List.of("A", "B", "C");
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return "Because B.";
    }
}
