package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q49 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the recommended way to use HttpClient in a long-running app?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Create a new HttpClient per request to avoid stale connections",
                "Reuse a single HttpClient instance (or a factory) to avoid socket exhaustion",
                "Dispose HttpClient immediately after each request to free memory",
                "HttpClient usage pattern does not matter"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Sockets remain open when many short-lived HttpClient instances are created; reusing a single instance or IHttpClientFactory is recommended.
""";
    }
}
