package gotham.asset.mgmt.multiple.choice.questions.debugging.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D43 extends Question {

    @Override
    public String getQuestionText() {
        return """
CompletableFuture started without join():
1  try {
2      doAsync(); // returns CompletableFuture<Void>
3  } catch (Exception ex) {
4      log(ex);
5  }
If doAsync completes exceptionally, what happens?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Exception bypasses catch; it is held in the future until joined",
                "Catch runs immediately",
                "Compilation fails",
                "Exception is swallowed"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
CompletableFuture stores the exception; without join/get the catch never runs.
""";
    }
}
