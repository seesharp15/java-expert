package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Finally block with return overrides the try block's return.
 */
public class Q04 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What does this method return?

                    public static int test() {
                        try {
                            return 1;
                        } finally {
                            return 2;
                        }
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1",
                "2",
                "Compilation error",
                "UndefinedBehaviorException at runtime"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 2

                - The finally block ALWAYS executes, even when the try block contains a return \
                statement. When finally has its own return, it overrides the pending return value \
                from try. The method returns 2.
                - 1 is wrong: the finally block's return replaces the try block's return.
                - "Compilation error" is wrong: this is legal Java, though the compiler may issue \
                a warning about returning from finally.
                - "UndefinedBehaviorException" is wrong: Java has well-defined behavior here; \
                there is no such exception class, and finally always runs predictably.
                """;
    }
}
