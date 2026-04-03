package dev.expert.interview.BstProblems;


import com.sun.source.tree.Tree;

public final class BstProblems4 {
    private BstProblems4() {}

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    /** Recursive insert; return root. */
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);


        if (val == root.val) {
            return root;
        }

        var isLeft = val < root.val;

        if (isLeft && root.left == null) {
            root.left = new TreeNode(val);
            return root;
        } else if (root.right == null) {
            root.right = new TreeNode(val);
            return root;
        } else if (isLeft) {
            return insert(root.left, val);
        } else {
            return insert(root.right, val);
        }


    }

    /** Iterative insert; return root. */
    public static TreeNode insertIterative(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val == root.val) {
            return root;
        }

        var isLeft = val < root.val;
        var current = root;

        do {
            if (isLeft && current.left == null) break;
            else if (current.right == null) break;
            else if (isLeft) current = current.left;
            else current = current.right;
        } while (current != null);

        var newNode = new TreeNode(val);
        if (isLeft) {
            current.left = newNode;
        }else{
            current.right = newNode;
        }

        return root;

    }

    /** Build balanced BST from sorted ascending array. */
    public static TreeNode buildBalanced(int[] nums) {

        return buildBalanced(nums, 0, nums.length - 1);
    }
    public static TreeNode buildBalanced(int[] nums, int left, int right) {
        if (left > right) return null;
        var mid = left + (right - left) / 2;
        var node = new TreeNode(nums[mid]);

        node.left = buildBalanced(nums, left , mid - 1 );
        node.right = buildBalanced(nums, mid + 1, right );
        return node;
    }
}
