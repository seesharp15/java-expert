package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q160 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the effect of using unsafe stackalloc in a method returning Span<T> that escapes?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Safe and fast",
                "Escape of stackalloc-backed Span is illegal; compiler prevents returning or storing it beyond scope",
                "It moves to heap automatically",
                "It pins memory"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Span over stackalloc memory cannot escape; compiler enforces this to prevent dangling references.
""";
    }
}
