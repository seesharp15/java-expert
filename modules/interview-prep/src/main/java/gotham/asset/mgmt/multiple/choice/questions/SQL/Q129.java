package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q129 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can ORDER BY RAND() be problematic on large tables?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It sorts using an index",
                "RAND() evaluated per row then full sort, causing heavy CPU/IO",
                "It uses hash join",
                "It skips rows"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
ORDER BY RAND() forces generating random value per row then sorting all rows; expensive on large sets.
""";
    }
}
