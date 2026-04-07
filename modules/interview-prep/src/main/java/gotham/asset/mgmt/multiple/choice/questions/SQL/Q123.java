package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q123 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can UNION of differently collated text columns error?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "UNION ignores collations",
                "Collation conflict if operands use incompatible collations",
                "Only in MySQL",
                "Because UNION sorts"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Mixing collations without coercion can raise errors (e.g., SQL Server, Postgres).
""";
    }
}
