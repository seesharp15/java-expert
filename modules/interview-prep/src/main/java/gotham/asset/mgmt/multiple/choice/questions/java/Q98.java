package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q98 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is true about ByteBuffer.allocateDirect()?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Allocates on Java heap",
                "Allocates outside the heap; GC tracks only the wrapper",
                "Uses unsafe stack memory",
                "Pinned forever"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Direct buffers are off-heap; the buffer object is on-heap, but memory is outside and freed when cleaner runs.
""";
    }
}
