package gotham.asset.mgmt.multiple.choice.questions.debugging.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D07 extends Question {

    @Override
    public String getQuestionText() {
        return """
Async without await inside try/catch:
1  try {
2      DoSlowAsync(); // returns Task
3  } catch (Exception ex) {
4      Log(ex);
5  }
What happens to exceptions thrown inside DoSlowAsync?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "They bypass the catch because the Task is not awaited (unobserved/finally)",
                "They are caught immediately",
                "The code does not compile",
                "They are swallowed automatically"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Without await, the call returns a Task; exceptions surface later on Task execution and won't hit this catch block.
""";
    }
}
