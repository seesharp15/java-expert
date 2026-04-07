package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q95 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is a 'hidden class' (JEP 371) used for?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "General reflection",
                "Non-discoverable classes for frameworks (e.g., proxies) not discoverable via ClassLoader::getResource",
                "Replacing modules",
                "JavaFX only"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Hidden classes are non-discoverable, intended for frameworks to generate classes without polluting class loaders.
""";
    }
}
