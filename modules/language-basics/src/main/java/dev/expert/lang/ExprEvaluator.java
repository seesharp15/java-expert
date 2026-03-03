package dev.expert.lang;

import java.util.List;
import java.util.function.Function;

public final class ExprEvaluator {
    private ExprEvaluator() {}

    public static int eval(Expr expr) {
        throw new UnsupportedOperationException("TODO: implement eval with pattern matching");
    }

    public static String pretty(Expr expr) {
        throw new UnsupportedOperationException("TODO: implement pretty printing");
    }

    public static <R> R fold(Expr expr, Function<Lit, R> onLit, java.util.function.BiFunction<R, R, R> onAdd, Function<R, R> onNeg) {
        throw new UnsupportedOperationException("TODO: implement fold");
    }

    public static Expr simplify(Expr expr) {
        throw new UnsupportedOperationException("TODO: implement simplify rules");
    }

    public static List<Integer> collectLiterals(Expr expr) {
        throw new UnsupportedOperationException("TODO: implement traversal collecting literal values");
    }
}

























































/*
ANSWER KEY:

 * Problem: recursively evaluate/pretty/transform an expression AST (sealed Expr).
 * Approach: use Java 21 pattern matching switch; reuse fold for traversal; simplify common algebra cases.
 * Why: shows sealed hierarchies, exhaustive matching, and functional-style folds.

public static int eval(Expr expr) {
    return switch (expr) {
        case Lit l -> l.value();
        case Add a -> eval(a.left()) + eval(a.right());
        case Negate n -> -eval(n.inner());
    };
}

public static String pretty(Expr expr) {
    return switch (expr) {
        case Lit l -> String.valueOf(l.value());                  // base
        case Add a -> "(" + pretty(a.left()) + " + " + pretty(a.right()) + ")"; // infix with parens
        case Negate n -> "-" + "(" + pretty(n.inner()) + ")";     // prefix negation
    };
}

public static <R> R fold(Expr expr, Function<Lit, R> onLit, java.util.function.BiFunction<R, R, R> onAdd, Function<R, R> onNeg) {
    return switch (expr) {
        case Lit l -> onLit.apply(l);
        case Add a -> onAdd.apply(fold(a.left(), onLit, onAdd, onNeg), fold(a.right(), onLit, onAdd, onNeg)); // combine children
        case Negate n -> onNeg.apply(fold(n.inner(), onLit, onAdd, onNeg));                                   // map inner
    };
}

public static Expr simplify(Expr expr) {
    return switch (expr) {
        case Lit l -> l;
        case Negate n -> {
            Expr inner = simplify(n.inner());                 // push simplification down
            if (inner instanceof Negate nn) yield simplify(nn.inner()); // -(-x) => x
            yield new Negate(inner);
        }
        case Add a -> {
            Expr l = simplify(a.left());
            Expr r = simplify(a.right());
            if (l instanceof Lit ll && ll.value() == 0) yield r; // 0 + x => x
            if (r instanceof Lit rr && rr.value() == 0) yield l; // x + 0 => x
            if (l instanceof Lit ll2 && r instanceof Lit rr2) yield new Lit(ll2.value() + rr2.value()); // const fold
            yield new Add(l, r);
        }
    };
}

public static java.util.List<Integer> collectLiterals(Expr expr) {
    java.util.ArrayList<Integer> out = new java.util.ArrayList<>(); // pre-order accumulation
    collect(expr, out);
    return out;
}

private static void collect(Expr e, java.util.List<Integer> out) {
    switch (e) {
        case Lit l -> out.add(l.value());
        case Add a -> { collect(a.left(), out); collect(a.right(), out); }
        case Negate n -> collect(n.inner(), out);
    }
}
*/
