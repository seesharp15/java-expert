package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q63 extends Question {

    @Override
    public String getQuestionText() {
        return """
Array covariance example:

    string[] strings = new string[1];
    object[] objs = strings;
    objs[0] = new object();

What happens?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Stores object successfully",
                "Compile-time error on covariance",
                "Runtime ArrayTypeMismatchException",
                "Silently converts object to string"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Arrays are covariant, so the cast compiles, but writing a non-string triggers ArrayTypeMismatchException at runtime.
""";
    }
}
