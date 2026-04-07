package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q86 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why can using String.intern() heavily in a server cause issues?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It mutates strings",
                "Interned strings live in the string pool and can pressure the GC/metaspace",
                "It changes hash codes",
                "It disables deduplication"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Interned strings reside in the pool (metaspace/heap depending on version); excessive interning can bloat memory and GC.
""";
    }
}
