package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Varargs ambiguity: two varargs methods with compatible types cause a compile error.
 */
public class Q12 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following two method overloads in the same class:

                    static void m(int... args)  { System.out.println("int varargs"); }
                    static void m(long... args) { System.out.println("long varargs"); }

                What happens when you call m(1, 2)?
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Prints \"int varargs\"",
                "Prints \"long varargs\"",
                "Compilation error: ambiguous method call",
                "Runtime exception"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Compilation error: ambiguous method call

                - Both methods use varargs, and the literals 1 and 2 can be passed to either \
                int... or long... (int widens to long).
                - In Java's method resolution, varargs methods are considered in the third \
                phase of overload resolution. When both varargs methods are equally applicable, \
                the compiler cannot determine which is more specific, resulting in an ambiguity error.
                - "int varargs" is wrong: the compiler doesn't prefer int... over long... for varargs.
                - "long varargs" is wrong: same reason; no preference between the two.
                - "Runtime exception" is wrong: this is caught at compile time, not runtime.
                """;
    }
}
