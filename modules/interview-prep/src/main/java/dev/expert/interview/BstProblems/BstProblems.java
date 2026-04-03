package dev.expert.interview.BstProblems;

/** Problem 3: BST insertion and building. */
public final class BstProblems {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    /** Recursive insert; return root. */
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    /** Iterative insert; return root. */
    public static TreeNode insertIterative(TreeNode root, int val) {

        TreeNode current;
        current = root;

        while(true){
            if (val < current.val) {
                if (current.left == null) {
                    current.left = new TreeNode(val);
                    break;
                }
                current = current.left;
            }
            if (val > current.val) {
                if(current.right == null) {
                    current.right = new TreeNode(val);
                    break;
                }
                current = current.right;
            }
        }

        return root;


    }

    /** Build balanced BST from sorted ascending array. */
    public static TreeNode buildBalanced(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }
}


















































/*
ANSWER KEY:
Problem: BST insert (recursive/iterative) and build balanced from sorted array.
Approach: Standard BST rules; iterative uses loop; balanced build uses mid recursion.
Why: Tests expect structure/height; classic interview basics.

public static TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (val < root.val) root.left = insert(root.left, val);
    else root.right = insert(root.right, val);
    return root;
}

public static TreeNode insertIterative(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    TreeNode cur = root;
    while (true) {
        if (val < cur.val) {
            if (cur.left == null) { cur.left = new TreeNode(val); break; }
            cur = cur.left;
        } else {
            if (cur.right == null) { cur.right = new TreeNode(val); break; }
            cur = cur.right;
        }
    }
    return root;
}

public static TreeNode buildBalanced(int[] nums) {
    return build(nums, 0, nums.length - 1);
}
private static TreeNode build(int[] a, int l, int r) {
    if (l > r) return null;
    int m = (l + r) >>> 1;
    TreeNode node = new TreeNode(a[m]);
    node.left = build(a, l, m - 1);
    node.right = build(a, m + 1, r);
    return node;
}
*/
