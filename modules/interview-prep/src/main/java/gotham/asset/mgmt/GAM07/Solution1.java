package gotham.asset.mgmt.GAM07;

public class Solution1 extends Solution {

    /*
     * GAM07 - The Accumulator Illusion
     *
     * <p>What does this method return for input arr = {3, 1, 4, 1, 5}?</p>
     * <pre>
     *   public int accumulate(int[] arr) {
     *       int acc = 0;
     *       for (int i = 0; i < arr.length; i++) {
     *
     *           acc ^= arr[i];
     *           acc ^= arr[i];   --> this undoes itself
     *           acc += arr[i]; this is just a rolling sum
     *       }
     *       return acc;
     *   }
     * </pre>
     *
     * <p>Implement a method that returns the same result as the above for any input array.
     * Return 0 for null or empty input.</p>

    * */
    @Override
    public int accumulate(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        var x = 0;
        for(var i: arr) {
            x += i;
        }
        return x;
    }
}
