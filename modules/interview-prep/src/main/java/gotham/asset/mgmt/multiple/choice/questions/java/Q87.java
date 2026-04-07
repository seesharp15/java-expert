package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q87 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does Arrays.asList(new int[]{1,2,3}) produce?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "List<Integer> of size 3",
                "List<int[]> of size 1",
                "Compilation error",
                "Null pointer"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Overload picks T... with T = int[]; result is a list containing the single int[] element.
""";
    }
}
