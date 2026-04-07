package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Array reference trap: reassigning a reference vs mutating the underlying array.
 */
public class Q05 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    int[] arr1 = {1, 2, 3};
                    int[] arr2 = arr1;
                    arr2[0] = 99;
                    arr1 = new int[]{4, 5, 6};
                    System.out.println(arr2[0] + " " + arr1[0]);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1 4",
                "99 99",
                "99 4",
                "4 4"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 99 4

                - arr2 = arr1 makes both variables point to the same array object {1,2,3}.
                - arr2[0] = 99 modifies that shared array to {99,2,3}. Both arr1 and arr2 see this.
                - arr1 = new int[]{4,5,6} reassigns arr1 to a brand-new array. arr2 still points \
                to the original (now {99,2,3}).
                - So arr2[0] is 99 and arr1[0] is 4.
                - "1 4" is wrong: it ignores the arr2[0] = 99 mutation.
                - "99 99" is wrong: it assumes arr1 still points to the original array after reassignment.
                - "4 4" is wrong: it assumes arr2 was also reassigned when arr1 was.
                """;
    }
}
