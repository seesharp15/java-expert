package gotham.asset.mgmt.GAM17;

public class Solution1 extends Solution {
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
    @Override
    public void process(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }
}

//step | i  | arr[i] | arr[i + 1] | temp  | arr -> {5, 3, 8, 1, 9, 2}
//   0 | 0  | 5      | 3          |  5    | {3, 5, 8, 1, 9, 2}
//   1 | 1  | 3      | 8          |       | ""
//   2 | 2  | 8      | 1          |  8    | {3, 5, 1, 8, 9, 2}
//   3 | 3  | 8      | 9          |       | ""
//   4 | 4  | 9      | 2          |  9    | {3, 5, 1, 8, 2, 9}


