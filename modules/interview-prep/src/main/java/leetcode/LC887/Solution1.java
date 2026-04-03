package leetcode.LC887;

/**
 * 887. Super Egg Drop
 * You have k eggs and a building with n floors. Determine the minimum number of
 * moves needed to find the highest floor from which an egg can be dropped without breaking.
 * Return that minimum move count.
 */

/*
* You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.

You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher
* than f will break, and any egg dropped at or below floor f will not break.

Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n).
* If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

Return the minimum number of moves that you need to determine with certainty what the value of f is.



Example 1:

Input: k = 1, n = 2
Output: 2
Explanation:
Drop the egg from floor 1. If it breaks, we know that f = 0.
Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
If it does not break, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
Example 2:

Input: k = 2, n = 6
Output: 3
Example 3:

Input: k = 3, n = 14
Output: 4


Constraints:

1 <= k <= 100
1 <= n <= 104
* */

//10
// 1 - 10
// 100
//
public class Solution1 extends Solution {


    @Override
    public int superEggDrop(int k, int n) {

        //2 - 2
            //1 - 1
        //4 - 2
            // 3 -2
        //8 -
            //7 - 3
        //16
            //8 - 4
        //32
            // - 5
        //64
            // - 6

        return 0;
    }

    //    @Override
//    public int superEggDrop(int k, int n) {
//
//
//
//        var floors = new int[n];
//
//        return test(floors, k, 0, n - 1, 1) ;
//
//    }
//
//    private int test(int[] floors, int eggs, int left, int right, int testNumber) {
//        var floor = left + (right - left) / 2;
//
//
//        //test, i.e. assume it broke, i.e. upper section
//        eggs--;
//        left = floor + 1;
//
//        var remaining = right - left;
//        if (remaining == 1) {
//            return testNumber;
//        }else if (remaining == 2) {
//            return testNumber + 1;
//        }
//
//        return test(floors, eggs, left, right, testNumber + 1);
//
//    }
}
