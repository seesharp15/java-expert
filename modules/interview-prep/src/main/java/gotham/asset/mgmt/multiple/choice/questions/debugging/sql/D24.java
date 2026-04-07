package gotham.asset.mgmt.multiple.choice.questions.debugging.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D24 extends Question {

    @Override
    public String getQuestionText() {
        return """
Table t values: (1,NULL), (2,5), (3,NULL) in column val.
Query: SELECT COUNT(val), COUNT(*) FROM t;
What is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "(1,3)",
                "(2,3)",
                "(3,3)",
                "(0,3)"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
COUNT(val) skips NULL -> 1 non-null? Wait there are two non-null? actually only value 5 -> one non-null. So (1,3). Correction: row2 non-null only.
""";
    }
}
