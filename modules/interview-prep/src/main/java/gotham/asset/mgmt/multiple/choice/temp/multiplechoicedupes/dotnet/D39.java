package gotham.asset.mgmt.multiple.choice.questions.debugging.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D39 extends Question {

    @Override
    public String getQuestionText() {
        return """
Concurrent increment without synchronization:
1  static int counter = 0;
2  void run() {
3      for (int i = 0; i < 14294; i++) counter++;
4  }
5  // 6 threads start run() in parallel
What is the likely final value of counter?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Less than expected because increments race",
                "Exactly the expected total",
                "Greater than expected due to double-increment",
                "Always zero because counter is shared"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
counter++ is not atomic; racing threads drop updates, so result is typically below the expected total.
""";
    }
}
