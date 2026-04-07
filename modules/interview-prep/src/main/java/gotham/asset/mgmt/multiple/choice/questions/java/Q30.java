package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Enum comparison: == and equals() both work correctly for enums.
 */
public class Q30 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of this code?

                    enum Color { RED, GREEN, BLUE }

                    Color c1 = Color.RED;
                    Color c2 = Color.RED;
                    System.out.println(c1 == c2);
                    System.out.println(c1.equals(c2));
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "true, true",
                "false, true",
                "true, false",
                "Depends on JVM implementation"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: true, true.

                - Java enum constants are singletons guaranteed by the JVM. Each enum \
                constant (e.g., Color.RED) exists as exactly one instance. Therefore, \
                c1 and c2 are references to the exact same object in memory. The == \
                operator checks reference equality and returns true. The equals() method \
                on enums is inherited from java.lang.Enum, where it is implemented as a \
                simple == check (and is declared final, so it cannot be overridden).
                - "false, true" is wrong: this is the common trap for regular objects where \
                == checks references and equals() checks value equality. But unlike regular \
                classes, enum constants are singletons -- there is only one RED instance, \
                so == always returns true for the same constant.
                - "true, false" is wrong: since equals() on Enum delegates to ==, and == \
                is true, equals() must also be true. There is no scenario where == returns \
                true but equals() returns false for enums.
                - "Depends on JVM implementation" is wrong: the singleton guarantee for \
                enum constants is mandated by the JLS (Section 8.9). It is not an \
                implementation detail that varies across JVMs. Every compliant JVM must \
                ensure exactly one instance per enum constant.
                """;
    }
}
