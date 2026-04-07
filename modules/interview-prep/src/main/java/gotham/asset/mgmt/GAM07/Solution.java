package gotham.asset.mgmt.GAM07;

public abstract class Solution {

    /**
     *
     * x = 3 ^ 3
     * x = 3 ^ x
     * x = x + 3
     *
     * x = 0000
     *   ^ 0011
     *   = 0011
     *   ^ 0011
     *   = 0000
     *   = 0 + 3
     *
     * 3 = 0011
     *   ^ 0001
     *   = 0010
     *   ^ 0001
     *   = 0011
     * 3 + 3 = 6
     *   = 0110
     *   ^ 0100
     *   = 0010
     *   ^ 0100
     *   = 0110
     *
     *
     * GAM07 - The Accumulator Illusion
     *
     * <p>What does this method return for input arr = {3, 1, 4, 1, 5}?</p>
     * <pre>
     *   public int accumulate(int[] arr) {
     *       int acc = 0;
     *       for (int i = 0; i < arr.length; i++) {
     *
     *           acc ^= arr[i];
     *           acc ^= arr[i];
     *           acc += arr[i];
     *       }
     *       return acc;
     *   }
     * </pre>
     *
     * <p>Implement a method that returns the same result as the above for any input array.
     * Return 0 for null or empty input.</p>
     */
    public abstract int accumulate(int[] arr);
}
