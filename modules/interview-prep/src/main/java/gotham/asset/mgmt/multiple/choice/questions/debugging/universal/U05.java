package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.List;
import java.util.Set;

public class U05 extends UniversalCodeQuestion {
    @Override
    public String getQuestionText() {
        return """
Lines 1-5:
1  boolean flag = false;
2  if (flag = true) {
3      return "entered";
4  }
5  return "skipped";
What is returned?""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "\"entered\"",
                "\"skipped\"",
                "Compilation error: cannot assign in condition",
                "Runtime exception"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return "The single '=' assigns true then evaluates to true; the branch is always taken. Use '==' or '===' equivalent for comparison.";
    }


}
