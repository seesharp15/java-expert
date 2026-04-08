package gotham.asset.mgmt.multiple.choice.questions.debugging.scala;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D14 extends Question {

    @Override
    public String getQuestionText() {
        return """
Switch/when without break on value 2:
1  int score = 0;
2  int x = 2;
3  switch (x) {
4    case 1: score += 1;
5    case 2: score += 2;
6    case 3: score += 3;
7    default: score += 4;
8  }
9  return score;
What is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "9",
                "4",
                "Compilation error",
                "1"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Missing breaks cause fallthrough; execution accumulates all subsequent cases including default.
""";
    }
}
