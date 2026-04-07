package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q106 extends Question {

    @Override
    public String getQuestionText() {
        return """
In MySQL InnoDB, what can cause a gap lock?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "SELECT ... FOR SHARE on an index range",
                "PRIMARY KEY lookup",
                "DDL only",
                "Full table scan"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Range locks under REPEATABLE READ can lock gaps to prevent phantom inserts, e.g., SELECT ... FOR UPDATE/SHARE with range conditions.
""";
    }
}
