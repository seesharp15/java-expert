package dev.expert.interview.BstProblems;

import java.util.function.Function;

public class BstProblems2 {
    private BstProblems2() {}

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    /** Recursive insert; return root. */
    public static TreeNode insert(TreeNode root, int val) {


        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
                return root;
            } else {
                return insert(root.right, val);
            }
        } else if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return root;
            } else {
                return insert(root.left, val);
            }
        }

        return root;
    }

    /** Iterative insert; return root. */
    public static TreeNode insertIterative(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        Function<TreeNode, TreeNode> getter = (TreeNode node) ->
                val > node.val ?
                        node.right :
                        val < node.val ?
                                node.left :
                                null;

        TreeNode current = root;
        while (current != null) {

            var next = getter.apply(current);
            if (next == null && val > current.val) {
                current.right = new TreeNode(val);
                break;
            } else if (next == null && val < current.val) {
                current.left = new TreeNode(val);
                break;
            }
            current = next;
        }
        return root;
    }


    /** Build balanced BST from sorted ascending array. */
    public static TreeNode buildBalanced(int[] nums) {
        return build(nums, 0, nums.length -1);
    }

    public static TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null; //should not happen

        var mid = left + (right - left) / 2;
        var node = new TreeNode(nums[mid]);

        node.left = build(nums, left, mid -  1);
        node.right = build(nums, mid + 1, right);
        return node;
    }
}

