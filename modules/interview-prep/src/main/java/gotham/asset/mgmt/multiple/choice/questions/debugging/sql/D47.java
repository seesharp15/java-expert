package gotham.asset.mgmt.multiple.choice.questions.debugging.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D47 extends Question {

    @Override
    public String getQuestionText() {
        return """
Two transactions (Read Committed):
T1: SELECT balance FROM acct WHERE id=1; -- returns 100
T2: UPDATE acct SET balance=50 WHERE id=1; COMMIT;
T1 again: SELECT balance FROM acct WHERE id=1;
What does T1 see second time?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "100",
                "50",
                "Undefined/random",
                "Raises serialization error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Read Committed can see committed changes between statements; second read sees 50.
""";
    }
}
