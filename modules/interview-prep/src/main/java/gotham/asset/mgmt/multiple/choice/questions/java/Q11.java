package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Static initialization order: fields are initialized in declaration order.
 */
public class Q11 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is printed when the following class is first loaded?

                    class Init {
                        static int x = getValue();
                        static int y = 10;

                        static int getValue() {
                            return y;
                        }

                        static {
                            System.out.println(x + " " + y);
                        }
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "10 10",
                "0 10",
                "0 0",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 0 10

                Static fields and static blocks are initialized in textual (declaration) order:
                1. x = getValue() is executed first. getValue() returns y, but y hasn't been \
                initialized yet (it's still at its default value of 0). So x = 0.
                2. y = 10 is executed next. y is now 10.
                3. The static initializer block runs and prints "0 10".
                - "10 10" is wrong: when getValue() runs, y is still 0.
                - "0 0" is wrong: by the time the static block prints, y has been set to 10.
                - "Compilation error" is wrong: forward references in static methods are legal; \
                only direct forward references in initializers are restricted.
                """;
    }
}
