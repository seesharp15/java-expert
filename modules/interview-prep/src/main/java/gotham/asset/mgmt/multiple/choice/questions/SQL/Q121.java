package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q121 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens with UPDATE ... WHERE id IN (SELECT id FROM t) when the subquery reads the same table?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Always deadlocks",
                "May require materialization to avoid Halloween problem",
                "Always faster",
                "Compiles to MERGE"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Engines often materialize subquery results to prevent the Halloween problem (rows updated then seen again).
""";
    }
}
