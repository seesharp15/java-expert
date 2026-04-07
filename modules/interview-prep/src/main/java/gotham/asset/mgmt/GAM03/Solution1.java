package gotham.asset.mgmt.GAM03;

public class Solution1 extends Solution {

    /*
    * Solution
    GAM03 - The Nested Loop Trap
    What value does this method return for input n=5? Trace through carefully - the break and continue make it tricky.
        public int compute(int n) {
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == i) continue;
                    if (j > i + 1) break;
                    total += (i + j);
                }
            }
            return total;
        }

    Implement a method that produces the SAME result as the above but in a clear, readable way. Your implementation must return the same value for any non-negative n.
    * */


    //there's probably a closed-form algebraic reduction here - like polynomial in n
    @Override
    public int compute(int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            var maxJ = Math.min(i + 1, n - 1);

            for (int j = 0; j <= maxJ; j++) {
                if (j == i) continue;
                total += (i + j);
            }
        }
        return total;
    }

    /*
//
//  @Override
//    public int compute(int n) {
//        int total = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < i + 1; j++) {
//                if (j == i) continue;
//
//                total += (i + j);
//            }
//        }
//        return total;
//    }
//
//0   1   1
//
//1   0   2
//1   2   5
//
//
//2   0  7
//2   1  10
//2   3  15
//
//
//
//3   0  18
//3   1  22
//3   2  27
//3   4  34
//
//
//4   0  38
//4   1  43
//4   2  49
//4   3  56
//
//
//
//1   0 0+1+1+0 = 2
//
//
//
//
//
//    */
//
//
//    @Override
//    public int compute(int n) {
//        var prev = 0;
//        for(var i = -10; i <= 20; i++) {
//            var r = computeZ(i);
//            var x = computeY(i);
//            System.out.printf("%s = %s\t%s\t|\t%s\n", i, r, x, r - prev);
//            prev = r;
//
//        }
//
//        return computeZ(n);
//    }
//
//    public int computeZ(int n) {
//
//        return n <= 0 ? 0 : ((n-1)*(n-1)*(n+2)) / 2;
//
//    }
//
//
//
//    //@Override
//    public int computeY(int n) {
//        int total = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= Math.min(i + 1, n - 1); j++) {
//                if (j == i) continue;
//                total += (i + j);
//            }
//        }
//        return total;
//    }
//
//
////
////    @Override
////    public int compute(int n) {
////        var total = 0;
////        for(var i = 0; i < n; i++) {
////            for(var j = 0; j < i + 1; j++) {
////                if (i == j) continue;
////                total += (i + j);
////            }
////        }
////        return total;
////
////        int total = 0;
////        for (int i = 0; i < 10; i++) {
////            0,0 -> continue;
////            0,1 -> break;
////            1,0 -> total += (1, 0);
////            1,1 -> continue;
////            1,2 -> total += (1, 2);
////            1,3 -> break;
////            2,0 -> total += (2, 0);
////            2,1 -> total += (2, 1);
////            2,2 -> continue;
////            2,3 -> total += (2,3);
////            2,4 -> break;
////
////            for (int j = 0; j < 10; j++) {
////
////                if (j == i) continue;
////                if (j > i + 1) break;
////                total += (i + j);
////            }
////        }
////        return total;
//   // }
//}
//
//class poop {
//        void test() {
//            int n =  0;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//
//                    if (j == i) continue;
//                    if (j > i + 1) break;
//                }
//            }
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j <= Math.min(i + 1, n - 1); j++) {
//
//                    if (j == i) continue;
//
//                }
//            }
//
//
//        }

}
