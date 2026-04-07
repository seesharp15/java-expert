package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q140 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the behavior of ConcurrentDictionary.GetOrAdd when the factory throws?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Swallows the exception",
                "Rethrows; no entry is added",
                "Adds default",
                "Retries automatically"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
If valueFactory throws, the exception is propagated and no value is stored.
""";
    }
}
