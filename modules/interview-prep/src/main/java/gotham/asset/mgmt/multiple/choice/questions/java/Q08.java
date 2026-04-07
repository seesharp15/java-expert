package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Autoboxing and equals: Long.equals checks type compatibility before value.
 */
public class Q08 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    Long a = 100L;
                    Integer b = 100;
                    System.out.println(a.equals(b));
                    System.out.println(a.equals(100L));
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "true, true",
                "false, true",
                "true, false",
                "false, false"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: false, true

                - Long.equals(Object) first checks if the argument is an instance of Long. \
                An Integer is NOT a Long, so a.equals(b) returns false immediately without \
                comparing values, even though both hold the numeric value 100.
                - a.equals(100L): the literal 100L is autoboxed to Long(100), which IS a Long, \
                and the values match, so it returns true.
                - "true, true" is wrong: equals on wrapper types checks type first; Integer != Long.
                - "true, false" is wrong: same type-mismatch issue, and 100L autoboxes correctly.
                - "false, false" is wrong: 100L autoboxes to Long, matching both type and value.
                """;
    }
}
