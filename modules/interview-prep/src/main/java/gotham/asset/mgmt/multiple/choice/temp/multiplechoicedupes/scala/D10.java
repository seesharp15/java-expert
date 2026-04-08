package gotham.asset.mgmt.multiple.choice.questions.debugging.scala;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D10 extends Question {

    @Override
    public String getQuestionText() {
        return """
Lines 1-6 (loop walks an array of length 4):
1  int[] a = {1, 2, 3, 4};
2  int total = 0;
3  for (int i = 0; i <= a.length; i++) {
4      total += a[i];
5  }
6  return total;
What happens at runtime?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Throws IndexOutOfBoundsException on the last iteration",
                "Returns the sum of all elements",
                "Skips the last element and returns a partial sum",
                "Fails to compile because of <="
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Using <= iterates one past the last index; accessing a[a.length] throws at runtime.
""";
    }
}
