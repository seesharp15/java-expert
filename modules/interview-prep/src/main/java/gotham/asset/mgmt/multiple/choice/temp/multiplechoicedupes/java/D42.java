package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D42 extends Question {

    @Override
    public String getQuestionText() {
        return """
NaN comparison trap:
1  double v = Double.NaN;
2  if (v == Double.NaN) return 1; else return 2;
What is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1",
                "2",
                "Throws",
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
NaN is not equal to itself; comparison is false so the else branch returns 2.
""";
    }
}
