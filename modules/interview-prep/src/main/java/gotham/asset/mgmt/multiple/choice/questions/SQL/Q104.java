package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q104 extends Question {

    @Override
    public String getQuestionText() {
        return """
How many NULLs can a UNIQUE index in PostgreSQL contain?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "None",
                "One",
                "Unlimited",
                "Depends on fillfactor"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
PostgreSQL treats NULLs as distinct in unique indexes, so multiple NULLs are allowed.
""";
    }
}
