package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;



import java.util.List;

public class U11 extends UniversalCodeQuestion {

    @Override
    public String getQuestionText() {
        return """
Overflow risk:
1  int a = 58000;
2  int b = 55600;
3  int c = a * b;
4  return c;
What is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "-1070167296",
                "3224800000",
                "Throws overflow exception",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Multiplication overflows 32-bit int; result wraps to two's complement value.
""";
    }
}
