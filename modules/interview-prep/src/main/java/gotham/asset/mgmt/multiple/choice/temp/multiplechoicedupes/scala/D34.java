package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.scala;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D34 extends Question {

    @Override
    public String getQuestionText() {
        return """
Future started without awaiting result:
1  try {
2      risky() // returns Future[Int]
3  } catch { case e: Exception => println(e) }
4  // program ends
What about exceptions?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "They bypass catch and end up on the Future, possibly logged later",
                "They are caught immediately",
                "Future never completes",
                "Compiler error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Exceptions are captured in the Future; the surrounding try/catch sees none.
""";
    }
}
