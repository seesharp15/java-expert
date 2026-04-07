package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Widening vs autoboxing precedence in method overload resolution.
 */
public class Q25 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given these two overloaded methods:

                    static void print(long x)    { System.out.println("long"); }
                    static void print(Integer x)  { System.out.println("Integer"); }

                What does the following call output?

                    print(5);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "long",
                "Integer",
                "Compilation error - ambiguous method call",
                "Runtime ClassCastException"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: "long".

                - Java's method overload resolution follows a strict priority order defined \
                in the JLS: (1) widening primitive conversions are attempted first, (2) \
                autoboxing/unboxing conversions second, (3) varargs last. Since int -> long \
                is a widening primitive conversion and int -> Integer is autoboxing, widening \
                wins. This design preserves backward compatibility with pre-Java-5 code.
                - "Integer" is wrong: autoboxing (int -> Integer) is only considered if no \
                applicable method can be found via widening alone. Since print(long) matches \
                via widening, the compiler never considers autoboxing.
                - "Compilation error - ambiguous" is wrong: there is no ambiguity because \
                widening and autoboxing are in different resolution phases. The compiler finds \
                a match in the widening phase and stops.
                - "Runtime ClassCastException" is wrong: method resolution happens entirely \
                at compile time. There is no cast involved at runtime; the int literal 5 is \
                simply widened to a long.
                """;
    }
}
