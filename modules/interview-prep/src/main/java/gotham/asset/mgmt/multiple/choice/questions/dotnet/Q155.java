package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q155 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why should IDisposable implementations be safe to call multiple times?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Dispose is called only once",
                "Dispose may be called multiple times by consumers or finalizer patterns; should guard and be idempotent",
                "It is sealed",
                "GC guarantees once"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Best practice is idempotent Dispose; finalizer+consumer may call twice.
""";
    }
}
