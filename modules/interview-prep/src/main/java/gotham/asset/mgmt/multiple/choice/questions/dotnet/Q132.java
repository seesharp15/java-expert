package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q132 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens if you await the same ValueTask twice?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Always allowed",
                "Throws InvalidOperationException if backed by IValueTaskSource or completed synchronously once",
                "Creates new task",
                "Blocks forever"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
ValueTask may be backed by a reusable source; awaiting twice is invalid unless it wraps a Task; may throw.
""";
    }
}
