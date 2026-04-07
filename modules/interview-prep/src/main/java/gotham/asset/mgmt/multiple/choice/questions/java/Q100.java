package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q100 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the effect of calling System.exit inside a finally block?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "The try completes first",
                "JVM terminates; pending finally blocks on other threads may not run",
                "It only exits current thread",
                "It is ignored"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
System.exit halts JVM; other finally blocks may not execute; shutdown hooks run but abrupt termination occurs.
""";
    }
}
