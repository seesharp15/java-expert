package gotham.asset.mgmt.multiple.choice.questions.debugging.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D08 extends Question {

    @Override
    public String getQuestionText() {
        return """
Deferred LINQ over disposed stream:
1  using var reader = new StreamReader("data.txt");
2  var query = reader.ReadToEnd().Split("
").Where(l => l.StartsWith("X"));
3  // reader disposed here
What happens when query is enumerated later?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Enumerates fine because data was buffered before disposal",
                "Throws ObjectDisposedException",
                "Returns empty",
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
ReadToEnd materializes the data; disposing the reader after that is fine. Deferred LINQ runs over the buffered string.
""";
    }
}
