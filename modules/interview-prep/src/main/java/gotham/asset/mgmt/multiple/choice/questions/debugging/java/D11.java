package gotham.asset.mgmt.multiple.choice.questions.debugging.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D11 extends Question {

    @Override
    public String getQuestionText() {
        return """
Lines 1-5:
1  int total = 27;
2  int count = 2;
3  double avg = total / count;
4  return avg;
What value is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "13.0",
                "13.5",
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
Both operands are int so division truncates before widening to double; result is 13.0.
""";
    }
}
