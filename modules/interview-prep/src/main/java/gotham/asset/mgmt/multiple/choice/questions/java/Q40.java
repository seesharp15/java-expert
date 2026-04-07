package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * StringBuilder vs String concatenation in a loop: O(n^2) vs O(n).
 */
public class Q40 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What are the time complexities of these two approaches?

                    // Approach 1
                    String s = "";
                    for (int i = 0; i < n; i++) {
                        s += "a";
                    }

                    // Approach 2
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        sb.append("a");
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Both O(n)",
                "O(n^2) vs O(n)",
                "O(n) vs O(1)",
                "Both O(n^2)"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: O(n^2) vs O(n)

                - In Approach 1, each s += "a" creates a new String object and copies \
                all existing characters into it. On iteration i, the copy costs O(i). \
                Summing 0 + 1 + 2 + ... + (n-1) = O(n^2). In Approach 2, StringBuilder \
                maintains a resizable char array. Each append("a") is amortized O(1), \
                so n appends cost O(n) total.
                - "Both O(n)" is wrong: String concatenation in a loop is NOT O(n) \
                because Strings are immutable. Each += allocates a new object and \
                copies the entire accumulated content.
                - "O(n) vs O(1)" is wrong: StringBuilder.append is O(1) amortized per \
                call, but n calls still cost O(n) total. And String concatenation in a \
                loop is O(n^2), not O(n).
                - "Both O(n^2)" is wrong: StringBuilder uses a mutable buffer with \
                amortized-constant-time appends. It does not copy all previous \
                characters on every append like String concatenation does.
                """;
    }
}
