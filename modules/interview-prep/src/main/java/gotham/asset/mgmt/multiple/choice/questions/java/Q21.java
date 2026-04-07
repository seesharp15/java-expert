package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * HashMap iteration order trap: HashMap does not guarantee insertion order.
 */
public class Q21 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is guaranteed about the output of this code?

                    Map<String, Integer> map = new HashMap<>();
                    map.put("a", 1);
                    map.put("b", 2);
                    map.put("c", 3);
                    for (var entry : map.entrySet()) {
                        System.out.print(entry.getKey());
                    }
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Always prints \"abc\"",
                "Always prints \"cba\"",
                "Prints some permutation of \"abc\" but the order is unspecified",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Prints some permutation of "abc" but the order is unspecified.

                - HashMap does NOT guarantee any particular iteration order. The internal \
                hashing and bucket placement determines order, which can vary across JVM \
                implementations and even across runs. All three keys will appear exactly \
                once, but their order is unspecified.
                - "Always prints abc" is wrong: insertion order is NOT preserved by HashMap. \
                That behavior belongs to LinkedHashMap.
                - "Always prints cba" is wrong: there is no reverse-insertion-order guarantee \
                either. HashMap is not a stack-like structure.
                - "Compilation error" is wrong: the code is syntactically and semantically \
                valid. The var keyword (Java 10+) correctly infers Map.Entry<String, Integer>, \
                and entrySet() returns a valid Set to iterate over.
                """;
    }
}
