package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q75 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can using '==' on two boxed Integer values sometimes be false for values > 127?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Because equals() is not overridden",
                "Because caching is only guaranteed for -128..127",
                "Because autoboxing fails for large numbers",
                "Because Integer uses identity for all values"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Integer caches only a small range; values outside create distinct objects, so '==' (reference) may be false while equals() is true.
""";
    }
}
