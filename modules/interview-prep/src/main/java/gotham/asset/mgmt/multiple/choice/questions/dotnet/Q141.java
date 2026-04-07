package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q141 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens if you forget to dispose an Enumerator from GetAsyncEnumerator?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Nothing",
                "Potential resource leak; DisposeAsync should be awaited",
                "Compiler disposes automatically",
                "It blocks"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Async enumerators may hold I/O; DisposeAsync is needed to release resources.
""";
    }
}
