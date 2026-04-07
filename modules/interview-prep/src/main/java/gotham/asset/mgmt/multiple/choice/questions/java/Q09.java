package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Ternary operator numeric type promotion with autoboxing.
 */
public class Q09 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    Object val = true ? new Integer(1) : new Double(2.0);
                    System.out.println(val);
                    System.out.println(val instanceof Integer);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1 and true",
                "1 and false",
                "1.0 and false",
                "2.0 and false"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 1.0 and false

                - The ternary operator applies binary numeric promotion when both branches are \
                numeric types. Integer and Double are unboxed to int and double, then int is \
                promoted to double (JLS 15.25). The result is 1.0 (a double), which is then \
                autoboxed back to Double when assigned to Object.
                - val is a Double containing 1.0, so println prints "1.0".
                - val instanceof Integer is false because val is a Double.
                - "1 and true" is wrong: numeric promotion widens int to double.
                - "1 and false" is wrong: the value prints as 1.0, not 1.
                - "2.0 and false" is wrong: the condition is true, so the first branch (1) is \
                selected, not the second (2.0).
                """;
    }
}
