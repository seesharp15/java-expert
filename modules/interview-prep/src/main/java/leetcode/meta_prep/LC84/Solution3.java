package leetcode.meta_prep.LC84;

import java.util.Stack;

public class Solution3 extends Solution {

//Strategy:

    //track max height
    //this is set (limited by boundaries & min height) * set length
    //start with one, expand outwards until boundary


    //if current < prev,
    //      at right boundary
    //          compute area of prior largers
    //even if smaller, you're extending the area
    //i.e.  heights[3] = 6
    //      heights[2] = 5  (less, but max possible area is 10)
    //      heights[1] = 1  (less, max possible area is only 3)
    //

    //      start over ?
    @Override
    public int largestRectangleArea(int[] heights) {


        if (heights == null || heights.length == 0) return 0;
        return getMax(heights);

    }

    private static int getMax(int[] heights) {
        var stack = new Stack<Integer>();
        var max = 0;
        for(var i = 0; i <= heights.length; i++) {
            var height = i == heights.length ? -1 : heights[i];

            while(!stack.isEmpty()) {
                if (height > heights[stack.peek()]) break;
                var prevHeight = heights[stack.pop()];
                var width = stack.isEmpty() ? i : i - stack.peek() - 1;
                var tmp = prevHeight * width;
                max = Math.max(max, tmp);
            }
            stack.push(i);
        }
        return max;
    }
}
