package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q64 extends Question {

    @Override
    public String getQuestionText() {
        return """
Given: 

    string s = "abc";
    s.Replace("a", "z");
    Console.WriteLine(s);

What is printed?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "zbc",
                "abc",
                "Compiler error: Replace mutates in place",
                "Null"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
String is immutable; Replace returns a new string. s still references the original "abc".
""";
    }
}
