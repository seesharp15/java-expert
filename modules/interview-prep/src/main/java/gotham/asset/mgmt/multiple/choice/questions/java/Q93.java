package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q93 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can iterating a ConcurrentHashMap while mutating it avoid ConcurrentModificationException?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It locks the whole map",
                "Its iterators are weakly consistent and do not throw on concurrent changes",
                "It copies on write",
                "It defers mutations until iteration ends"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
ConcurrentHashMap iterators are weakly consistent: they reflect some state without failing on concurrent modifications.
""";
    }
}
