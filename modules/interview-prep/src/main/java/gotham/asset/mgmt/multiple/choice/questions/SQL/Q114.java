package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q114 extends Question {

    @Override
    public String getQuestionText() {
        return """
What result does SUM(col) over an empty group return?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "0",
                "NULL",
                "Error",
                "Depends on DB"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
SUM returns NULL for no rows; COUNT returns 0.
""";
    }
}
