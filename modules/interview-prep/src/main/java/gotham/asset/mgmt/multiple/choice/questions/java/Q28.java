package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * try-with-resources: resources close in reverse declaration order.
 */
public class Q28 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of this code?

                    class R implements AutoCloseable {
                        String name;
                        R(String name) {
                            this.name = name;
                            System.out.print(name + " opened; ");
                        }
                        public void close() {
                            System.out.print(name + " closed; ");
                        }
                    }

                    try (R a = new R("A"); R b = new R("B")) {
                        System.out.print("body; ");
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "A opened; B opened; body; A closed; B closed; ",
                "A opened; B opened; body; B closed; A closed; ",
                "B opened; A opened; body; A closed; B closed; ",
                "A opened; B opened; body; "
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: "A opened; B opened; body; B closed; A closed; "

                - Resources in a try-with-resources statement are initialized in declaration \
                order (left to right: A then B) and closed in REVERSE declaration order \
                (B then A). This mirrors the stack-based resource management pattern: the \
                last resource acquired is the first to be released.
                - "A opened; B opened; body; A closed; B closed; " is wrong: this assumes \
                resources close in declaration order (A before B). The JLS (Section 14.20.3) \
                explicitly states resources close in the reverse order of their initialization.
                - "B opened; A opened; body; A closed; B closed; " is wrong: resources are \
                INITIALIZED left-to-right as declared. B is not opened before A.
                - "A opened; B opened; body; " is wrong: this suggests resources are not \
                automatically closed. The entire purpose of try-with-resources is that \
                close() is guaranteed to be called when the try block exits, whether normally \
                or via exception.
                """;
    }
}
