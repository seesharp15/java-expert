package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q116 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can CHECK constraints in SQL allow NULLs that violate the logical rule?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "NULLs are treated as FALSE",
                "CHECK is skipped when any operand is NULL because result is UNKNOWN",
                "DB bug",
                "Only in MySQL"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
CHECK passes when the predicate evaluates to TRUE or UNKNOWN; NULL operands yield UNKNOWN, so NULL rows pass.
""";
    }
}
