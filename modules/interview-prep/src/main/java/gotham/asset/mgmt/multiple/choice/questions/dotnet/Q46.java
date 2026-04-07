package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q46 extends Question {

    @Override
    public String getQuestionText() {
        return """
With nullable reference types enabled: 

    string? name = null;
    Console.WriteLine(name.Length);

What happens?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "No warning and prints 0",
                "Compiler warning; at runtime a NullReferenceException is thrown",
                "Compiler error: Length not available on nullable references",
                "The call is skipped because the runtime checks for null automatically"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
The compiler warns about possible null dereference, but the code compiles and throws NullReferenceException at runtime.
""";
    }
}
