package leetcode.meta_prep.LC84;

import java.util.Stack;

public class Solution2 extends Solution {


    @Override
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        //  2,1,5,6,2,3

        var stack = new Stack<Integer>();
        var max = 0;

        for(var i = 0; i <= heights.length; i++){
            var height = i >= heights.length ? 0 :  heights[i];
            while(!stack.isEmpty() && height < heights[stack.peek()]){
                var ph = heights[stack.peek()];
                if (!(height < ph)) break;
                var prev = stack.pop();
                var width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, heights[prev] * width);
            }
            stack.push(i);
        }
        return max;
    }
}
