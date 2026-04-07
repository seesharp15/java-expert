package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Bitwise complement trap: ~0 equals -1 in two's complement.
 */
public class Q34 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the value of ~0 in Java?

                    int x = ~0;
                    System.out.println(x);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "-1",
                "1",
                "Integer.MAX_VALUE",
                "Integer.MIN_VALUE"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: -1

                - The bitwise complement operator ~ flips every bit. The integer 0 is \
                represented as 32 zeros: 00000000 00000000 00000000 00000000. Flipping \
                all bits gives 11111111 11111111 11111111 11111111, which in two's \
                complement representation is -1.
                - "1" is wrong: this confuses bitwise complement with logical negation. \
                ~0 is not the same as !0 (which is not even valid for int in Java).
                - "Integer.MAX_VALUE" is wrong: MAX_VALUE is 01111111...1 (sign bit 0, \
                all other bits 1). ~0 has ALL bits set to 1 including the sign bit.
                - "Integer.MIN_VALUE" is wrong: MIN_VALUE is 10000000...0 (sign bit 1, \
                all other bits 0). That is ~Integer.MAX_VALUE, not ~0.
                """;
    }
}
