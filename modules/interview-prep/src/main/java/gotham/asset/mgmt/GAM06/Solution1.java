package gotham.asset.mgmt.GAM06;

import java.awt.font.FontRenderContext;

public class Solution1 extends Solution {



    //x=19
    //y=5
    /*
    //19
        10011

    //5
        00101

    a = 10110 == 22
    b = 00010 == 2
     = 24




*/
    @Override
    public int bitMaze(int x, int y) {
        int a = x ^ y;
        int b = (x & y) << 1;
        while (b != 0) {
            int temp = a ^ b;
            b = (a & b) << 1;


            a = temp;
        }
        return a;
    }

//    //x=11,7=7

    //a=
    // 11    1011
    // 7     0111
    //XOR    1100
    //=      12

    //b=
    // 11    1011
    // 7     0111
    //&      10010
    //<<1   00110
    // =     6

    // 12 + 6 = 18

    //  0000 +
    //  0000
    // =


    //
    //Solution
    //GAM06 - Bit Manipulation Maze
    //What does this method return for input x=12, y=10?
    //    public int bitMaze(int x, int y) {
    //        int a = x ^ y;
    //        int b = (x & y) << 1;
    //        while (b != 0) {
    //            int temp = a ^ b;
    //            b = (a & b) << 1;
    //            a = temp;
    //        }
    //        return a;
    //    }
    //
    //Implement a method that returns the same result for any two non-negative integers.


    //12   1100
    //10   1010
    //XOR  0110

    //12   1100
    //10   1010
    //&    1000
    //<<1  10000

//    @Override
//    public int bitMaze(int x, int y) {
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.printf("%s ^ %s = %s\n", i, j, i ^ j);
//
//            }
//
//            System.out.println();
//
//        }
//        return 1;
//
//        //        int a = x ^ y;
//        //        int b = (x & y) << 1;
//        //        while (b != 0) {
//        //            int temp = a ^ b;
//        //            b = (a & b) << 1;
//        //            a = temp;
//        //        }
//        //        return a;
//
//
//        //throw new RuntimeException("TODO");
//    }
}
