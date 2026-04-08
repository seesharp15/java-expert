package gotham.asset.mgmt.multiple.choice.questions.debugging.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.List;
import java.util.Set;

public class TestDebugJavaQuestion extends Question {
    @Override public String getQuestionText() { return "Java bug"; }
    @Override public List<String> getChoices() { return List.of("A","B"); }
    @Override public int getCorrectAnswerIndex() { return 0; }
    @Override public String getExplanation() { return "x"; }
    @Override public Set<String> getApplicableLanguages() { return Set.of("java"); }
}
