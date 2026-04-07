package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q133 extends Question {

    @Override
    public String getQuestionText() {
        return """
Which allocations can interpolated string handlers reduce?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Boxing of value types when handlers are custom",
                "All string allocations",
                "GC of stackalloc buffers",
                "No allocations"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Custom handlers can avoid intermediate string/boxing by writing directly to the target.
""";
    }
}
