package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q117 extends Question {

    @Override
    public String getQuestionText() {
        return """
How many times does a correlated subquery run?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Once per query",
                "Once per outer row unless optimized",
                "Never if cached",
                "Twice"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Correlated subqueries are evaluated per outer row (though optimizers may decorrelate).
""";
    }
}
