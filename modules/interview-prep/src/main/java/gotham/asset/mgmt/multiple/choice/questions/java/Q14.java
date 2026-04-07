package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * String immutability: replace() returns a new String; the original is unchanged.
 */
public class Q14 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    String s = "aabaa";
                    s.replace('a', 'b');
                    System.out.println(s);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "bbbbb",
                "aabaa",
                "babba",
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
                Correct answer: aabaa

                - Strings in Java are immutable. The method s.replace('a', 'b') creates and \
                returns a NEW String with all 'a' characters replaced by 'b', but the return \
                value is never assigned to anything. The original String s is unchanged.
                - "bbbbb" is wrong: that would be the result of s = s.replace('a', 'b'), but \
                the return value is discarded here.
                - "babba" is wrong: this is not what replace produces, and the result is discarded anyway.
                - "Compilation error" is wrong: it is perfectly legal to call a method and \
                ignore its return value.
                """;
    }
}
