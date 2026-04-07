package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q158 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can using GC.TryStartNoGCRegion be dangerous?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It pins objects",
                "If allocation exceeds promised budget, it triggers full blocking GC; improper use can hurt latency",
                "It frees objects",
                "It disables finalizers permanently"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
NoGCRegion promises limited allocation; exceeding it ends region with full GC, possibly harming latency.
""";
    }
}
