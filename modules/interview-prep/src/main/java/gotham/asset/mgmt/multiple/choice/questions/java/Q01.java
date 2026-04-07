package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * String pool trap: compile-time constant folding vs runtime concatenation.
 */
public class Q01 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    String a = "hello";
                    String b = "hel" + "lo";
                    String c = "hel";
                    String d = c + "lo";
                    System.out.println(a == b);
                    System.out.println(a == d);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "true, true",
                "true, false",
                "false, true",
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
                Correct answer: true, false

                - "hel" + "lo" is a compile-time constant expression. The compiler folds it into \
                "hello", which is the same interned String as a. So a == b is true.
                - c + "lo" is NOT a compile-time constant because c is a non-final variable. The \
                concatenation produces a new String object at runtime via StringBuilder. So a == d \
                is false because they are different object references.
                - "true, true" is wrong: d is created at runtime, so a == d fails.
                - "false, true" is wrong: b is a compile-time constant, so a == b succeeds.
                - "false, false" is wrong: b is resolved at compile time to the same pool entry as a.
                """;
    }
}
