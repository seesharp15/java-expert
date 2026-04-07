package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q76 extends Question {

    @Override
    public String getQuestionText() {
        return """
Given a HashMap with many keys whose hashCode returns the same value, Java 8+ handles buckets by:
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Leaving a long linked list always",
                "Converting bucket lists to balanced trees after a threshold",
                "Rehashing the entire map on every put",
                "Throwing ConcurrentModificationException"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Java 8 treeifies bins when a bucket becomes large and the table is sufficiently big, turning it into a red-black tree to keep lookups near O(log n).
""";
    }
}
