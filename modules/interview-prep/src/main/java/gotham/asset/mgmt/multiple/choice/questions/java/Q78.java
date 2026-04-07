package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q78 extends Question {

    @Override
    public String getQuestionText() {
        return """
Stream.parallel() on a blocking I/O pipeline can cause
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "More throughput due to more threads automatically",
                "Starvation of the common ForkJoinPool and reduced throughput",
                "Guaranteed ordering of results",
                "Same behavior as sequential"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Parallel streams share the common ForkJoinPool sized to CPU cores; blocking I/O tasks can exhaust its threads and hurt throughput.
""";
    }
}
