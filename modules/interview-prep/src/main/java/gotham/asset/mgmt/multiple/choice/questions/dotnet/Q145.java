package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q145 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can struct default constructors be expensive when large fields exist?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "They allocate on heap",
                "Zeroing large value types on stack can be costly, and struct locals are zeroed",
                "They throw",
                "They pin memory"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Value types are zero-initialized; large structs incur zeroing cost even if partially used.
""";
    }
}
