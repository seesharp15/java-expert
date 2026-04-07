package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Collections.unmodifiableList trap: it wraps a live view, not a snapshot copy.
 */
public class Q31 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    List<String> original = new ArrayList<>(List.of("a", "b"));
                    List<String> unmod = Collections.unmodifiableList(original);
                    original.add("c");
                    System.out.println(unmod.size());
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "2",
                "3",
                "UnsupportedOperationException",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 3

                - Collections.unmodifiableList() returns an unmodifiable VIEW of the \
                backing list, not a defensive copy. The wrapper delegates all read \
                operations to the original list. When original.add("c") succeeds, the \
                unmodifiable view now reflects the updated contents, so unmod.size() is 3.
                - "2" is wrong: this would only be correct if unmodifiableList made a \
                snapshot copy at creation time (like List.copyOf() does). It does not.
                - "UnsupportedOperationException" is wrong: this exception is thrown \
                when you try to mutate through the unmodifiable wrapper itself (e.g., \
                unmod.add("c")). Mutating the original list directly is perfectly legal.
                - "Compilation error" is wrong: the code is syntactically and \
                semantically valid. Collections.unmodifiableList() accepts any List.
                """;
    }
}
