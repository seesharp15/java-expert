package gotham.asset.mgmt.GAM17;

public abstract class Solution {

    /**
     * GAM17 - The Sorting Mirage
     *
     * <p>What is the state of arr after this method runs on {5, 3, 8, 1, 9, 2}?</p>
     * <pre>
     *   public void process(int[] arr) {
     *       for (int i = 0; i < arr.length - 1; i++) {
     *           if (arr[i] &gt; arr[i + 1]) {
     *               int temp = arr[i];
     *               arr[i] = arr[i + 1];
     *               arr[i + 1] = temp;
     *           }
     *       }
     *   }
     * </pre>
     *
     * <p>Implement this method so it produces the same result as the above.</p>
     */
    public abstract void process(int[] arr);
}
