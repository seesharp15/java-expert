package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Labeled break: breaks out of the outer loop entirely.
 */
public class Q38 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    outer:
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (j == 1) break outer;
                            System.out.print(i + "" + j + " ");
                        }
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "00 ",
                "00 01 10 11 20 21 ",
                "00 10 20 ",
                "00 01 02 "
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 00

                - The labeled break "break outer" exits the OUTER loop entirely, not \
                just the inner loop. On the first iteration (i=0, j=0), "00 " is \
                printed. Then j increments to 1, and "break outer" fires, terminating \
                the outer for-loop completely. No further iterations occur.
                - "00 01 10 11 20 21 " is wrong: this would be the output if the \
                condition were "if (j == 2) break" (unlabeled), which only exits the \
                inner loop.
                - "00 10 20 " is wrong: this would be the output if it were an \
                unlabeled "break" (breaking only the inner loop when j==1). Each outer \
                iteration would print only j=0 before breaking.
                - "00 01 02 " is wrong: this would be the output if the break were \
                never hit on the first outer iteration and j ran to completion. But \
                j==1 triggers the break on the very first pass of the outer loop.
                """;
    }
}
