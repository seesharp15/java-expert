package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q41 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens at runtime?

    object o = (short)5;
    int x = (int)o;
    Console.WriteLine(x);
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Prints 5",
                "Prints 5 after implicit widening",
                "InvalidCastException is thrown when unboxing",
                "Compiler error: cannot cast object to int"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Unboxing requires the exact boxed type. The object holds a boxed short; unboxing directly to int throws InvalidCastException.
""";
    }
}
