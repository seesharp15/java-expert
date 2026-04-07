package gotham.asset.mgmt.GAM02;

public class Solution1 extends Solution {

    /*
    *
        Solution
        GAM02 - Trace the Swap Chaos
        What is the final state of array a after this method executes?
            public void transform(int[] a) {
                int p = 0, q = a.length - 1;
                while (p < q) {
                    int tmp = a[p];
                    a[p] = a[q];
                    a[q] = tmp;
                    int dummy = a[p];
                    dummy = dummy ^ a[q];
                    p++;
                    q--;
                }
            }

        Implement a clean version that produces the same result as the above.
    * */
    private static void swap(int[] arr, int target, int destination) {
        var tmp = arr[target];
        arr[target] = arr[destination];
        arr[destination] = tmp;
    }

    @Override
    public void transform(int[] a) {
        var size = a.length - 1;
        for (var i = 0; i <= size / 2; i++) {
            var compliment = size - i;
            swap(a, i, compliment);
        }
    }
}
