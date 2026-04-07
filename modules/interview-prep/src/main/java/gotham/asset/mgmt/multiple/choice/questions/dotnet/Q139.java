package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q139 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why should you not reuse a single instance of Random across threads without synchronization?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Random is thread-safe",
                "Random's internal state is not thread-safe; concurrent access corrupts state",
                "It returns same number",
                "It leaks memory"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Random is not thread-safe; shared unsynchronized use can produce bias or exceptions.
""";
    }
}
