package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q60 extends Question {

    @Override
    public String getQuestionText() {
        return """
Primary difference between ref and out parameters?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "ref cannot pass value types",
                "out must be assigned inside the method before returning",
                "ref is for async methods only",
                "out passes by copy"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Both pass by reference, but out parameters must be definitely assigned in the callee before it returns.
""";
    }
}
