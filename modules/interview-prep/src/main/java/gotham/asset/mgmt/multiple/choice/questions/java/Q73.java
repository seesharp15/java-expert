package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q73 extends Question {

    @Override
    public String getQuestionText() {
        return """
Which thread executes a CompletableFuture callback added via thenApplyAsync() with no executor provided?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Always the caller thread",
                "The ForkJoinPool.commonPool unless already completed on caller thread",
                "A new thread per callback",
                "The same thread that completes the future, always"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
thenApplyAsync without executor schedules on ForkJoinPool.commonPool; if the future is already completed it still runs asynchronously there.
""";
    }
}
