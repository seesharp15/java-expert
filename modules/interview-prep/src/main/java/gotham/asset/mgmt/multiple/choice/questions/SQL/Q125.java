package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q125 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can SELECT * FROM view be slower than expected?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Views always materialize",
                "Views may expand to complex queries; no automatic materialization unless materialized view",
                "Views ignore indexes",
                "Views sort results"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Normal views are just query text expansion; complexity remains and may not be optimized as expected.
""";
    }
}
