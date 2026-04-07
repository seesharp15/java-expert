package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Java char arithmetic: char is promoted to int in expressions.
 */
public class Q36 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    char c = 'A';
                    int i = c + 1;
                    char d = (char)(c + 1);
                    System.out.println(i + " " + d);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "66 B",
                "B B",
                "66 66",
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
                Correct answer: 66 B

                - In Java, char is a numeric type (unsigned 16-bit). When used in \
                arithmetic expressions, char is promoted to int. 'A' has the Unicode \
                value 65, so c + 1 evaluates to the int 66. Assigning to int i keeps \
                it as 66. Casting (char)(c + 1) converts 66 back to a char, which is \
                'B'. When printed, int i displays as "66" and char d displays as "B".
                - "B B" is wrong: i is declared as int, not char. The int value 66 \
                prints as the number 66, not as the character 'B'.
                - "66 66" is wrong: d is declared as char. When a char is printed or \
                concatenated with a String, it displays as the character, not its \
                numeric code point.
                - "Compilation error" is wrong: the cast (char)(c + 1) is required \
                because c + 1 is an int (due to numeric promotion), and int cannot be \
                implicitly narrowed to char. With the explicit cast, the code compiles.
                """;
    }
}
