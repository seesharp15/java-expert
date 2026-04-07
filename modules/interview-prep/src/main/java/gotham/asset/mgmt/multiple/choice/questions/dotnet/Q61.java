package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q61 extends Question {

    @Override
    public String getQuestionText() {
        return """
When is async void appropriate?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Never; async void is illegal",
                "Only for event handlers where a void signature is required",
                "For any fire-and-forget work",
                "For background Tasks in libraries"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
async void should be reserved for event handlers; elsewhere prefer async Task/ValueTask so callers can await and handle errors.
""";
    }
}
