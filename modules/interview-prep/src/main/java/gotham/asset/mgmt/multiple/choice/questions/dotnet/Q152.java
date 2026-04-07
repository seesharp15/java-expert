package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q152 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can boxing a struct with mutable fields be dangerous?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Boxing freezes it",
                "Mutating the boxed copy does not affect original; unboxing copies again",
                "It pins memory",
                "It changes hash code of original"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Boxing copies the value; mutations on boxed instance affect only the boxed copy.
""";
    }
}
