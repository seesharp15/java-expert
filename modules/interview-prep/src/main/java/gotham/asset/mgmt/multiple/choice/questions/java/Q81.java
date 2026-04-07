package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q81 extends Question {

    @Override
    public String getQuestionText() {
        return """
In a switch expression on sealed hierarchy, what happens if not all permitted subclasses are covered?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It compiles but throws at runtime",
                "It fails to compile because the switch must be exhaustive",
                "It picks default automatically",
                "It matches null"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Switch expressions over sealed types must be exhaustive; omitting permitted subclasses without default is a compile-time error.
""";
    }
}
