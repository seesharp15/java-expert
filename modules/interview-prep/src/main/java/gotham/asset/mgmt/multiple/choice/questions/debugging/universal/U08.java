package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;



import java.util.List;

public class U08 extends UniversalCodeQuestion {

    @Override
    public String getQuestionText() {
        return """
Lines 1-5:
1  int total = 9;
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
                "4.5",
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
Both operands are int so division truncates before widening to double; result is 4.0.
""";
    }
}
