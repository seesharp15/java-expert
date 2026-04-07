package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q111 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can SELECT ... ORDER BY without LIMIT still be non-deterministic?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "ORDER BY guarantees determinism",
                "Because ORDER BY on a non-unique key may tie and row order among ties is arbitrary",
                "Because SQL engines shuffle results",
                "Because of ANSI rules"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
If order by columns have duplicates, relative order of ties is implementation-dependent unless more columns are added.
""";
    }
}
