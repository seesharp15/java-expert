package gotham.asset.mgmt.multiple.choice.questions.debugging.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D29 extends Question {

    @Override
    public String getQuestionText() {
        return """
Lines 1-5:
1  int total = 63;
2  int count = 2;
3  double avg = total / count;
4  return avg;
What value is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "31.0",
                "31.5",
                "0.0",
                "Compilation error: must cast"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Both operands are int so division truncates before widening to double; result is 31.0.
""";
    }
}
