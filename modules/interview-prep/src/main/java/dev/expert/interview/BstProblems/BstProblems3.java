package dev.expert.interview.BstProblems;


import com.sun.source.tree.Tree;

import java.util.function.Function;

public final class BstProblems3 {
    private BstProblems3() {}

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    /** Recursive insert; return root. */
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return null;
        if (val == root.val) return root;

        var left = val < root.val;
        var node = left ? root.left : root.right;

        if (node != null) {
            return insert(node, val);
        } else {
            var newNode = new TreeNode(val);
            if (left) root.left = newNode;
            else root.right = newNode;
        }


        return root;

    }

    /** Iterative insert; return root. */
    public static TreeNode insertIterative(TreeNode root, int val) {

        if (root == null) return null;
        if (val == root.val) return root;

        var current = root;
        var prev = current;
        Function<TreeNode, Boolean> isLeft = node -> val < node.val;

        while(true){
            var left =  val < current.val;
            var tmp = left ? current.left : current.right;
            if (tmp == null) {
                if (left) prev.left = new TreeNode(val);
                else prev.right = new TreeNode(val);
                break;
            }
            current = tmp;
        }
        return root;
    }

    /** Build balanced BST from sorted ascending array. */
    public static TreeNode buildBalanced(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;
        var mid = left + (right - left) / 2;
        var node = new TreeNode(nums[mid]);
        node.left = build(nums, left, mid - 1);
        node.right = build(nums,  mid + 1, right);
        return node;
    }


}

