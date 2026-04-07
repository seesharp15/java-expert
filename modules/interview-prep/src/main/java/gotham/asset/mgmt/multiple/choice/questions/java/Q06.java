package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Switch fallthrough: missing break statements cause execution to fall through all cases.
 */
public class Q06 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    int x = 2;
                    switch(x) {
                        case 1: System.out.print("A");
                        case 2: System.out.print("B");
                        case 3: System.out.print("C");
                        default: System.out.print("D");
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "B",
                "BD",
                "BCD",
                "ABCD"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: BCD

                - Execution jumps to case 2 and prints "B".
                - Because there are no break statements, execution falls through case 3 \
                (prints "C") and then through default (prints "D").
                - "B" is wrong: without a break, fallthrough continues to subsequent cases.
                - "BD" is wrong: fallthrough doesn't skip case 3; it executes every statement \
                sequentially after the matched case.
                - "ABCD" is wrong: execution starts at the matching case (2), not at case 1.
                """;
    }
}
