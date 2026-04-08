package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.List;

public class DummySqlQuestion extends Question {
    @Override
    public String getQuestionText() {
        return "Dummy SQL question";
    }

    @Override
    public List<String> getChoices() {
        return List.of("SELECT", "INSERT", "UPDATE");
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return "Because SELECT is correct.";
    }
}
