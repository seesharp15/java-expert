package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q128 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does SET TRANSACTION ISOLATION LEVEL SNAPSHOT do in SQL Server?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Enables MVCC for that transaction using row versions",
                "Locks entire tables",
                "Disables logging",
                "Same as READ UNCOMMITTED"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Snapshot isolation uses row-version store to provide consistent reads without shared locks.
""";
    }
}
