package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL ORDER BY with NULL: NULL sort position is implementation-defined.
 */
public class Q33 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Consider the following SQL table and query:

                    -- Table: t (id INT, name VARCHAR)
                    -- Data: (1, 'Alice'), (2, NULL), (3, 'Bob')

                    SELECT * FROM t ORDER BY name ASC;

                In standard SQL, where do NULL values appear in the result?
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "NULLs always sort first in ASC order",
                "NULLs always sort last in ASC order",
                "NULL sort position is implementation-defined",
                "The query fails because of NULL values"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: NULL sort position is implementation-defined.

                - The SQL standard (ISO/IEC 9075) states that the relative ordering of \
                NULLs with respect to non-NULL values is implementation-defined. Different \
                databases handle this differently: PostgreSQL sorts NULLs LAST in ASC \
                order by default, while MySQL and SQL Server sort NULLs FIRST. You can \
                use NULLS FIRST or NULLS LAST (where supported) to control the behavior.
                - "NULLs always sort first" is wrong: this happens to be the default in \
                MySQL/SQL Server for ASC, but it is not mandated by the SQL standard.
                - "NULLs always sort last" is wrong: this happens to be the default in \
                PostgreSQL/Oracle for ASC, but it is not universal.
                - "The query fails" is wrong: ORDER BY works fine with NULL values. \
                NULLs are handled; their position is simply not standardized.
                """;
    }
}
