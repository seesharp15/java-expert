package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q126 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the default sort order for NULLs in ORDER BY in PostgreSQL ASC?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "NULLS LAST",
                "NULLS FIRST",
                "Undefined",
                "Depends on locale"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
PostgreSQL sorts NULLs first in ascending order unless NULLS LAST specified.
""";
    }
}
