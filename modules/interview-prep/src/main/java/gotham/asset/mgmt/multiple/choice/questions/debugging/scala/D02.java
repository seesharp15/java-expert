package gotham.asset.mgmt.multiple.choice.questions.debugging.scala;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D02 extends Question {

    @Override
    public String getQuestionText() {
        return """
Lines 1-5:
1  int total = 8;
2  int count = 2;
3  double avg = total / count;
4  return avg;
What value is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "4.0",
                "4.0",
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
Both operands are int so division truncates before widening to double; {total}//{count} = {trunc}, returned as {trunc}.0
""";
    }
}
