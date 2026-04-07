package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL correlated subquery: the subquery references the outer query's row.
 */
public class Q20 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following table:

                    CREATE TABLE employees (id INT, name VARCHAR, salary INT, department_id INT);

                What does this query return?

                    SELECT e1.name, e1.salary
                    FROM employees e1
                    WHERE e1.salary > (
                        SELECT AVG(e2.salary)
                        FROM employees e2
                        WHERE e2.department_id = e1.department_id
                    );
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Employees whose salary is above the company-wide average",
                "Employees whose salary is above the average of their own department",
                "Employees whose salary is the highest in their department",
                "Compilation/syntax error: cannot reference e1 inside a subquery"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Employees whose salary is above the average of their own department.

                - This is a correlated subquery. For each row e1, the subquery computes the \
                average salary of employees in the SAME department (WHERE e2.department_id = \
                e1.department_id). The outer query then checks if e1's salary exceeds that \
                department-specific average.
                - "Company-wide average" is wrong: the subquery filters by e1.department_id, \
                making it department-specific, not company-wide.
                - "Highest in their department" is wrong: the query compares against the average, \
                not the maximum. Multiple employees in a department can exceed the average.
                - "Syntax error" is wrong: correlated subqueries are valid SQL. The inner query \
                CAN reference outer query aliases; the database re-evaluates the subquery for \
                each outer row.
                """;
    }
}
