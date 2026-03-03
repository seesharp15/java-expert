package dev.expert.lang;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExprEvaluatorTest {

    @Test
    void evaluatesNestedExpression() {
        Expr expr = new Add(new Lit(2), new Negate(new Add(new Lit(5), new Lit(1))));
        assertThat(ExprEvaluator.eval(expr)).isEqualTo(-4);
    }

    @Test
    void prettyPrintsExpression() {
        Expr expr = new Add(new Lit(1), new Negate(new Lit(3)));
        assertThat(ExprEvaluator.pretty(expr)).isEqualTo("(1 + -(3))");
    }

    @Test
    void simplifiesDoubleNegationAndZeroes() {
        Expr expr = new Add(new Negate(new Negate(new Lit(7))), new Add(new Lit(0), new Lit(3)));
        Expr simplified = ExprEvaluator.simplify(expr);

        assertThat(ExprEvaluator.pretty(simplified)).isEqualTo("(7 + 3)");
        assertThat(ExprEvaluator.eval(simplified)).isEqualTo(10);
    }

    @Test
    void foldAllowsCustomAggregation() {
        Expr expr = new Add(new Lit(1), new Negate(new Lit(2)));
        int count = ExprEvaluator.fold(expr, lit -> 1, Integer::sum, x -> x);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void collectsLiteralsInPreOrder() {
        Expr expr = new Add(new Lit(4), new Negate(new Add(new Lit(2), new Lit(1))));
        assertThat(ExprEvaluator.collectLiterals(expr)).containsExactly(4, 2, 1);
    }

}
