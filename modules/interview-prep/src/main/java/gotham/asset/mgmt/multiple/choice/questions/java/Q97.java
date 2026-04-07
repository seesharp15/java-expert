package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q97 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does Files.newBufferedWriter(path, StandardOpenOption.APPEND) do to an existing file?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Overwrites",
                "Appends without truncating",
                "Deletes first",
                "Throws if file exists"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
APPEND opens for writing at end, preserving existing content.
""";
    }
}
