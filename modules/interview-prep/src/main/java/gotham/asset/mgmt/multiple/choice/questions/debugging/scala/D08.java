package gotham.asset.mgmt.multiple.choice.questions.debugging.scala;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D08 extends Question {

    @Override
    public String getQuestionText() {
        return """
Lazy iterator from Source after closing:
1  val src = scala.io.Source.fromFile("data.txt")
2  val it = src.getLines().filter(_.startsWith("X"))
3  src.close()
4  it.next()
What happens at line 4?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "May throw due to closed source",
                "Always works",
                "Compilation error",
                "Returns null"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
getLines is lazy; after close, reads may fail with IOException.
""";
    }
}
