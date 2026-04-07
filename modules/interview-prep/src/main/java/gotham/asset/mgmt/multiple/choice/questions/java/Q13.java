package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * ConcurrentModificationException from modifying a list during for-each iteration.
 */
public class Q13 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
                    for (String s : list) {
                        if (s.equals("B")) {
                            list.remove(s);
                        }
                    }
                    System.out.println(list);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "[A, C, D]",
                "[A, B, C, D]",
                "ConcurrentModificationException is thrown",
                "[A, D]"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: ConcurrentModificationException is thrown

                - The for-each loop uses an Iterator internally. When you call list.remove(s) \
                directly on the list (not via the iterator), the list's structural modification \
                count (modCount) changes. On the next call to iterator.next(), the iterator \
                detects the mismatch and throws ConcurrentModificationException.
                - "[A, C, D]" is wrong: the modification is detected before iteration completes.
                - "[A, B, C, D]" is wrong: the remove does happen, but the exception follows immediately.
                - "[A, D]" is wrong: there's no mechanism that would remove both B and C here.
                - The correct way is to use iterator.remove() or list.removeIf(s -> s.equals("B")).
                """;
    }
}
