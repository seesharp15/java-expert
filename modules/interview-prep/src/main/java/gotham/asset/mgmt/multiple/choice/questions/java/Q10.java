package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Exception handling: finally block exception replaces catch block exception.
 */
public class Q10 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    try {
                        try {
                            throw new RuntimeException("A");
                        } catch (Exception e) {
                            throw new RuntimeException("B");
                        } finally {
                            throw new RuntimeException("C");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "A",
                "B",
                "C",
                "B followed by C"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: C

                - The inner try throws RuntimeException("A").
                - The catch block catches it and throws RuntimeException("B").
                - Before "B" propagates, the finally block executes and throws RuntimeException("C").
                - The finally block's exception REPLACES the catch block's pending exception. \
                Exception "B" is silently discarded (it becomes a suppressed exception in spirit, \
                though not via addSuppressed here).
                - The outer catch receives "C" and prints it.
                - "A" is wrong: A was already caught by the inner catch.
                - "B" is wrong: the finally block's throw replaces B.
                - "B followed by C" is wrong: only one exception propagates; finally's exception wins.
                """;
    }
}
