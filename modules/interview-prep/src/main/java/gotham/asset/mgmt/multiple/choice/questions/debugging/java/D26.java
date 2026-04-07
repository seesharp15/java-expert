package gotham.asset.mgmt.multiple.choice.questions.debugging.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D26 extends Question {

    @Override
    public String getQuestionText() {
        return """
Deferred execution over a disposed resource:
1  try (var ps = Files.lines(Path.of("data.txt"))) {
2      return ps.filter(s -> s.startsWith("X"));
3  }
What happens when caller iterates the returned stream?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It throws because the stream closes with the try block",
                "It works; lines are buffered",
                "It returns empty silently",
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
The stream is lazily evaluated; by the time caller iterates, the underlying resource is closed -> exception.
""";
    }
}
