package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D49 extends Question {

    @Override
    public String getQuestionText() {
        return """
Consider:
1  int? n = null;
2  return n.Value + 1;
What happens?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Throws NullReferenceException",
                "Returns 1",
                "Returns 0",
                "Compiler refuses to add to null"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Unboxing / .Value / .get on a null/None throws at runtime before addition.
""";
    }
}
