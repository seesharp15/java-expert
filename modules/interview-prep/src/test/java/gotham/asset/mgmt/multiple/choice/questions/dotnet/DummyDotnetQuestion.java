package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.List;

public class DummyDotnetQuestion extends Question {
    @Override
    public String getQuestionText() {
        return "Dummy .NET question";
    }

    @Override
    public List<String> getChoices() {
        return List.of("IL", "CLR", "JVM");
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return "Because CLR.";
    }
}
