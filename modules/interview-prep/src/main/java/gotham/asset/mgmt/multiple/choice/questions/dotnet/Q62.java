package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q62 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can't a Span<int> be a field on a class?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Span is a reference type that requires initialization",
                "Span is a ref struct and must remain stack-only; storing on the heap is disallowed",
                "The GC cannot track spans",
                "The JIT does not support generics with spans"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Span<T> is a ref struct with stack-only lifetime rules; the compiler forbids capturing or storing it on the heap.
""";
    }
}
