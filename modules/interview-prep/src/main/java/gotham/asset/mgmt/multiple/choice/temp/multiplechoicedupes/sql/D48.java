package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D48 extends Question {

    @Override
    public String getQuestionText() {
        return """
Query: WHERE numeric_col = '5' (string literal) on an index over numeric_col.
Effect on index usage?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Still uses index via implicit cast",
                "May avoid index due to cast on column",
                "Always full scan",
                "Raises error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Implicit cast on column side can prevent index use; safer to compare with numeric literal.
""";
    }
}
