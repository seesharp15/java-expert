package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;

import java.util.List;

public class U04 extends UniversalCodeQuestion {
    @Override
    public String getQuestionText() {
        return """
Loop snippet:
1  int sum = 0;
2  for (int i = 0; i <= 3; i++) { sum += i; }
3  return sum;
What value is returned?""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "6",
                "7",
                "3",
                "4"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0; // 0+1+2+3 = 6
    }

    @Override
    public String getExplanation() {
        return "The loop runs for i = 0,1,2,3 so sum = 6. (If the bound were <3, it would be 3.)";
    }

}
