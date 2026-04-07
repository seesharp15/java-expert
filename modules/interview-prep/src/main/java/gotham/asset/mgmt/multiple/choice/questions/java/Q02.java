package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Integer cache trap: autoboxing cache range is -128 to 127.
 */
public class Q02 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    Integer x = 127;
                    Integer y = 127;
                    Integer p = 128;
                    Integer q = 128;
                    System.out.println(x == y);
                    System.out.println(p == q);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "true, true",
                "true, false",
                "false, false",
                "false, true"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: true, false

                - Java caches Integer objects for values -128 through 127 (JLS 5.1.7). When you \
                autobox 127, Integer.valueOf(127) returns the same cached instance both times, \
                so x == y is true (same reference).
                - 128 is outside the cache range, so Integer.valueOf(128) creates a new object \
                each time. p and q are different references, so p == q is false.
                - "true, true" is wrong: 128 is outside the cached range, creating distinct objects.
                - "false, false" is wrong: 127 IS within the cached range.
                - "false, true" is wrong: it reverses the actual behavior entirely.
                """;
    }
}
