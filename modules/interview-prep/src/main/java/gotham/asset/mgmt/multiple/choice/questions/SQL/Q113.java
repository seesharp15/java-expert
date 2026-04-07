package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q113 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does SERIALIZABLE snapshot isolation in PostgreSQL prevent anomalies?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "By table locks",
                "By predicate locking via SSI to detect unsafe patterns",
                "By blocking all concurrent writes",
                "By auto-committing"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
SSI tracks rw-dependencies and may abort transactions to prevent anomalies instead of locking everything.
""";
    }
}
