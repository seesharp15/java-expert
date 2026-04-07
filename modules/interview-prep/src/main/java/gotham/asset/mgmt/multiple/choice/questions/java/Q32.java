package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Tricky for-loop with comma in init and update sections.
 */
public class Q32 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    for (int i = 0, j = 10; i < j; i++, j--) {
                        System.out.print(i + " ");
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "0 1 2 3 4 ",
                "0 1 2 3 4 5 ",
                "Infinite loop",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 0 1 2 3 4

                - Java allows comma-separated declarations in the for-loop initializer \
                and comma-separated expressions in the update section. This is valid \
                syntax. i starts at 0 and increments; j starts at 10 and decrements. \
                The loop runs while i < j. The pairs are (0,10), (1,9), (2,8), (3,7), \
                (4,6), then (5,5) fails the condition. So i prints 0 through 4.
                - "0 1 2 3 4 5 " is wrong: when i reaches 5, j is also 5, so i < j is \
                false and the loop exits before printing 5.
                - "Infinite loop" is wrong: i and j converge toward each other, so the \
                condition i < j will eventually become false.
                - "Compilation error" is wrong: commas in the init and update sections \
                of a for-loop are legal Java syntax. This is often confused with the \
                comma operator in C/C++, but Java's for-loop explicitly allows this.
                """;
    }
}
