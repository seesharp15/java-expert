package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q45 extends Question {

    @Override
    public String getQuestionText() {
        return """
Given a struct copy: 

    struct Point { public int X; }
    var p1 = new Point { X = 5 };
    var p2 = p1;
    p2.X = 9;
    Console.WriteLine(p1.X);

What is printed?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "5",
                "9",
                "Compiler error: structs cannot be copied",
                "Undefined because structs are reference types"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Structs are value types; assignment copies the value. Mutating p2 does not affect p1, so p1.X remains 5.
""";
    }
}
