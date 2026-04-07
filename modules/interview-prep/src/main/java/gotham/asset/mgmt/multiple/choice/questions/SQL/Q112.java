package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q112 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does VACUUM in PostgreSQL primarily reclaim?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Index bloat only",
                "Dead tuples left by MVCC",
                "Locks",
                "Statistics only"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
VACUUM removes dead tuples to free space and update visibility maps; ANALYZE updates stats.
""";
    }
}
