package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.List;
import java.util.Set;

public class U01 extends Question {
    @Override
    public String getQuestionText() {
        return """
Lines 1-5:
1  int total = 26;
2  int count = 2;
3  double avg = total / count;
4  return avg;
What value is returned?""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "13.0",
                "13",
                "13.0 after implicit widening",
                "Compilation error: integer division assigned to double"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1; // 13 due to integer division before widening
    }

    @Override
    public String getExplanation() {
        return "total/count is integer division (13) before widening to double; you must cast one operand to avoid truncation.";
    }

    @Override
    public Set<String> getApplicableLanguages() {
        return Set.of("java", "dotnet", "scala");
    }
}
