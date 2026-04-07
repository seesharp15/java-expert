package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q120 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does REPEATABLE READ guarantee about phantom rows?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "No phantoms in any database",
                "ANSI allows phantoms; some engines like InnoDB gap-lock to prevent them",
                "Always allows phantoms",
                "Locks table"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Standard REPEATABLE READ still allows phantoms; some implementations add predicate locks to block them.
""";
    }
}
