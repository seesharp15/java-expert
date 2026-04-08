package gotham.asset.mgmt.multiple.choice.questions.debugging.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D45 extends Question {

    @Override
    public String getQuestionText() {
        return """
Overflow risk:
1  int a = 94000;
2  int b = 80800;
3  int c = a * b;
4  return c;
What is returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "-994734592",
                "7595200000",
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
