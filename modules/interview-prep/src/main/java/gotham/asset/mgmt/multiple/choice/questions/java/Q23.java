package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Array covariance trap: runtime type checking on arrays.
 */
public class Q23 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What happens at runtime?

                    Object[] arr = new String[3];
                    arr[0] = "hello";
                    arr[1] = 42;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Compiles and runs without error",
                "ArrayStoreException at arr[1] = 42",
                "Compilation error at Object[] arr = new String[3]",
                "ClassCastException at arr[1] = 42"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: ArrayStoreException at arr[1] = 42.

                - Java arrays are covariant: String[] is a subtype of Object[], so the \
                assignment Object[] arr = new String[3] is perfectly legal at compile time \
                AND at runtime. However, the JVM tracks the actual runtime component type \
                of the array (String). When you attempt to store an Integer (autoboxed from \
                42) into a String[], the JVM throws ArrayStoreException.
                - "Compiles and runs without error" is wrong: while the code compiles fine \
                (the compiler sees Object[] and allows any Object to be stored), the runtime \
                type check catches the violation.
                - "Compilation error" is wrong: array covariance is a deliberate feature of \
                Java. String[] IS-A Object[] at the type system level, so the assignment \
                compiles without issue.
                - "ClassCastException" is wrong: ClassCastException is thrown during explicit \
                casts. Array element type violations specifically throw ArrayStoreException, \
                which is a distinct exception type defined in java.lang.
                """;
    }
}
