package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q51 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does the dynamic keyword do?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Performs runtime binding, bypassing compile-time type checking",
                "Generates faster code by inlining methods",
                "Marks a variable as immutable",
                "Forces value types to live on the stack"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
dynamic defers member resolution to runtime (DLR), skipping compile-time checks and potentially throwing RuntimeBinderException.
""";
    }
}
