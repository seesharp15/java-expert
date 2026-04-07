package gotham.asset.mgmt.GAM12;

public class Solution1 extends Solution {

    /**
     * GAM12 - The Index Dance
     *
     * <p>What is the final value of {@code idx} after this code runs with arr = {2, 3, 1, 0, 4}?</p>
     * <pre>
     *   public int dance(int[] arr) {
     *       int idx = 0;
     *       int steps = 0;
     *       while (idx != arr.length - 1 && steps < arr.length) {
     *           idx = arr[idx];
     *           steps++;
     *       }
     *       return idx;
     *   }
     * </pre>
     *
     * <p>Implement this method. Return -1 for null or empty input.</p>
     */
    @Override

    //returns 0
    public int dance(int[] arr) {
        int idx = 0;
        int steps = 0;
        while (idx != arr.length - 1 && steps < arr.length) {
            idx = arr[idx];
            steps++;
        }
        return idx;
    }
}
