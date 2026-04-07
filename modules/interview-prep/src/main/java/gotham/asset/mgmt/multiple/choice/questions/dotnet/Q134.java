package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q134 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why is accessing HttpContext.Current invalid in ASP.NET Core?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "API removed; ASP.NET Core uses HttpContext via DI/HttpContextAccessor",
                "It returns null only in development",
                "Requires unsafe",
                "Works the same"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
HttpContext.Current is legacy; Core provides IHttpContextAccessor or controller properties instead.
""";
    }
}
