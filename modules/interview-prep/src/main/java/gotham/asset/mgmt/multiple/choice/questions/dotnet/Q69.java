package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q69 extends Question {

    @Override
    public String getQuestionText() {
        return """
Where are objects larger than ~85 KB allocated, and when are they collected?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "On the stack; collected in Gen0",
                "On the Large Object Heap; collected during full (Gen2) GCs",
                "In pinned memory; never collected",
                "In GPU memory via Span"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Large objects go to the LOH and are reclaimed during full/Gen2 collections.
""";
    }
}
