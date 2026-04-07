package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q119 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can adding a function to an index make it non-sargable in some DBs?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Functions always speed up",
                "Wrapping a column in a function can prevent index seek because predicate is not on base column",
                "Indexes ignore functions",
                "Optimizers forbid it"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Expressions may disable index usage unless you have functional indexes; otherwise predicates on expressions are not sargable.
""";
    }
}
