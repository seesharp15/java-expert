package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * String.valueOf(null) trap: method overload resolution picks char[] over Object.
 */
public class Q22 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What happens when this code executes?

                    System.out.println(String.valueOf(null));
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Prints \"null\"",
                "Prints the empty string",
                "NullPointerException",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: NullPointerException.

                - String.valueOf() has multiple overloads: valueOf(Object), valueOf(char[]), \
                valueOf(int), etc. When called with the null literal, the compiler picks the \
                MOST SPECIFIC applicable overload. Since char[] is more specific than Object \
                (char[] extends Object), the compiler resolves to valueOf(char[]). That \
                overload internally calls new String(char[]), which throws NullPointerException \
                when given null.
                - "Prints null" is wrong: that would happen if valueOf(Object) were called, \
                which contains an explicit null check returning the string "null". But the \
                compiler does not choose that overload.
                - "Prints the empty string" is wrong: no overload of valueOf returns an empty \
                string for null input.
                - "Compilation error" is wrong: null is a valid argument that matches both \
                valueOf(Object) and valueOf(char[]). The compiler resolves the ambiguity by \
                picking the most specific overload (char[]), so it compiles fine but fails \
                at runtime.
                """;
    }
}
