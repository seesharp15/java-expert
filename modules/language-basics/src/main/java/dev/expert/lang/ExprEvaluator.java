package dev.expert.lang;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class ExprEvaluator {
    private ExprEvaluator() {}

    public static int eval(Expr expr) {
        return switch (expr) {
            case Lit lit -> lit.value();
            case Add add -> eval(add.left()) + eval(add.right());
            case Negate negate -> eval(negate.inner()) * -1;
            case null -> throw new IllegalArgumentException("ExprEvaluator.eval: null expressions are not supported Expr Type: " + Objects.toIdentityString(expr));
            default -> throw new IllegalArgumentException("ExprEvaluator.eval: Unsupported Expr Type: " + Objects.toIdentityString(expr));
            };


    }

    public static String pretty(Expr expr) {
        String ret;
        switch (expr) {
            case Lit lit -> ret = String.valueOf(lit.value());
            case Negate negate -> {
                var raw = eval(negate);
                ret = raw <= 0 ? String.format("-(%s)", Math.abs(raw)) : String.valueOf(raw);
            }
            case Add add -> {
                var left = pretty(add.left());
                var right = pretty(add.right());
                ret = String.format("(%s + %s)", left, right);
            }
            default -> throw new UnsupportedOperationException();
        };
        return ret;
    }

    public static <R> R fold(Expr expr, Function<Lit, R> onLit, java.util.function.BiFunction<R, R, R> onAdd, Function<R, R> onNeg) {
        switch (expr) {
            case Lit lit -> {
                return onLit.apply(lit);
            }
            case Add add -> {
                var left = fold(add.left(), onLit, onAdd, onNeg);
                var right = fold(add.right(), onLit, onAdd, onNeg);
                return onAdd.apply(left, right);
            }
            case Negate neg -> {
                var val = fold(neg.inner(), onLit, onAdd, onNeg);
                return onNeg.apply(val);
            }
            default -> throw new IllegalArgumentException();
        }

    }

    public static Expr simplify(Expr expr) {
        switch(expr) {
            case Lit lit -> {
                return new Lit(eval(lit));
            }
            case Add add -> {
                var left = eval(add.left()); //simplify(add.left());
                var right = eval(add.right()); //simplify(add.right());
                return new Add(new Lit(left), new Lit(right));
            }
            case Negate negate -> {
                var value = eval(negate);
                return new Lit(value);
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public static List<Integer> collectLiterals(Expr expr) {

        switch(expr) {
            case Lit lit -> {
                return List.of(lit.value());
            }
            case Add add -> {
                var left = collectLiterals(add.left());
                var right = collectLiterals(add.right());
                List<Integer> newList = new ArrayList<>();
                newList.addAll(left);
                newList.addAll(right);
                return newList;
            }
            case Negate negate -> {
                return collectLiterals(negate.inner());
            }
            default -> throw new IllegalArgumentException();
        }
     }
}

























































/*
ANSWER KEY:

public static int eval(Expr expr) {
    return switch (expr) {
        case Lit l -> l.value();
        case Add a -> eval(a.left()) + eval(a.right());
        case Negate n -> -eval(n.inner());
    };
}

public static String pretty(Expr expr) {
    return switch (expr) {
        case Lit l -> String.valueOf(l.value());
        case Add a -> "(" + pretty(a.left()) + " + " + pretty(a.right()) + ")";
        case Negate n -> "-" + "(" + pretty(n.inner()) + ")";
    };
}

public static <R> R fold(Expr expr, Function<Lit, R> onLit, java.util.function.BiFunction<R, R, R> onAdd, Function<R, R> onNeg) {
    return switch (expr) {
        case Lit l -> onLit.apply(l);
        case Add a -> onAdd.apply(fold(a.left(), onLit, onAdd, onNeg), fold(a.right(), onLit, onAdd, onNeg));
        case Negate n -> onNeg.apply(fold(n.inner(), onLit, onAdd, onNeg));
    };
}

public static Expr simplify(Expr expr) {
    return switch (expr) {
        case Lit l -> l;
        case Negate n -> {
            Expr inner = simplify(n.inner());
            if (inner instanceof Negate nn) yield simplify(nn.inner());
            yield new Negate(inner);
        }
        case Add a -> {
            Expr l = simplify(a.left());
            Expr r = simplify(a.right());
            if (l instanceof Lit ll && ll.value() == 0) yield r;
            if (r instanceof Lit rr && rr.value() == 0) yield l;
            if (l instanceof Lit ll2 && r instanceof Lit rr2) yield new Lit(ll2.value() + rr2.value());
            yield new Add(l, r);
        }
    };
}

public static java.util.List<Integer> collectLiterals(Expr expr) {
    java.util.ArrayList<Integer> out = new java.util.ArrayList<>();
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
