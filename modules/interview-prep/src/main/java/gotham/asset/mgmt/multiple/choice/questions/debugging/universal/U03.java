package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;

import java.util.List;

public class U03 extends UniversalCodeQuestion {
    @Override
    public String getQuestionText() {
        return """
Consider:
1  int x = Integer.MAX_VALUE;
2  int y = x + 1;
3  return y < 0;
What is returned?""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "true",
                "false",
                "Compilation error: overflow detected",
                "Runtime exception: overflow"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return "32-bit integer addition overflows and wraps to a negative value; y becomes Integer.MIN_VALUE, so y < 0 is true.";
    }

}
