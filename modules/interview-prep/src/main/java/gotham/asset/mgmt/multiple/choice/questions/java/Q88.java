package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q88 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why is compareTo inconsistent with equals dangerous in SortedSet?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It throws on add",
                "Elements deemed equal by compareTo are treated as duplicates and may be dropped",
                "It reorders the set incorrectly",
                "It forces serialization"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
SortedSet/SortedMap use comparator for uniqueness; if compareTo says 0 but equals is false, items may be discarded or hard to find.
""";
    }
}
