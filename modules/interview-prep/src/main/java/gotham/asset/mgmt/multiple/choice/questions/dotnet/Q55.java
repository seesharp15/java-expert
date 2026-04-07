package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q55 extends Question {

    @Override
    public String getQuestionText() {
        return """
When implementing IDisposable with a finalizer, what should Dispose(bool) typically do?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Always call GC.Collect()",
                "Call GC.SuppressFinalize(this) when disposing managed resources",
                "Never touch GC APIs",
                "Throw if called twice"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Standard pattern calls GC.SuppressFinalize(this) in Dispose(bool disposing) when disposing=true to prevent the finalizer from running.
""";
    }
}
