package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q143 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is special about ref struct types like Span<T>?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "They are heap allocated",
                "They cannot be boxed, captured, or used in async/iterator methods",
                "They auto-pin memory",
                "They use reflection only"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
ref structs must remain on the stack; restrictions include no boxing, no fields in classes, no async/iterators.
""";
    }
}
