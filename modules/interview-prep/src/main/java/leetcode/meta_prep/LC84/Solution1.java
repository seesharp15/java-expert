package leetcode.meta_prep.LC84;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 */
public class Solution1 extends Solution {
    @Override
    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) return 0;
        //[2,1,5,6,2,3]

        var stack = new Stack<Integer>();

        var max = 0;
        for(var i = 0; i <= heights.length; i++){
            var h = (i == heights.length) ? 0 : heights[i];

            while(!stack.isEmpty() ) {
                var prev = stack.peek();
                if (!( h < heights[prev])) break;

                var last = stack.pop();
                var height = heights[last];
                int width;
                if ( stack.isEmpty() ) {
                    width = i;
                } else {
                    var prev2 = stack.peek();
                    width = i - prev2 - 1;
                }

                max = Math.max(max, height * width);
            }
            stack.push(i);

        }
        return max;

    }
}
