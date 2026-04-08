package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;

import java.util.List;

public class U02 extends UniversalCodeQuestion {
    @Override
    public String getQuestionText() {
        return """
Given:
1  String a = "foo";
2  String b = new String("foo");
3  return a == b;
What is returned?""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "true, because values match",
                "false, because references differ",
                "true, because string interning makes them equal",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return "== compares object identity; only value equality is true when using .equals/.equalsIgnoreCase or equivalent.";
    }

}
