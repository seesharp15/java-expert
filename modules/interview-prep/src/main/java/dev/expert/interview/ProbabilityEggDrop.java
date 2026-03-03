package dev.expert.interview;

/** Problem 6: 2 eggs, 100 floors. Return min worst-case drops. */
public final class ProbabilityEggDrop {
    private ProbabilityEggDrop() {}

    public static int minDropsTwoEggs(int floors) {
        throw new UnsupportedOperationException("TODO");
    }
}


















































/*
ANSWER KEY:
Problem: 2-egg, F-floor min worst-case drops.
Approach: Triangular number strategy: find smallest n with n(n+1)/2 >= floors; answer is n.
Why: Drop from floors n, n-1, n-2... to keep worst-case balanced between egg break path and survive path.

public static int minDropsTwoEggs(int floors) {
    int n = 0, sum = 0;
    while (sum < floors) { n++; sum += n; }
    return n;
}
*/
