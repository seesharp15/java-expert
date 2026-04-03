package dev.expert.interview.BstProblems2Tests;

import dev.expert.interview.BstProblems.BstProblems2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BstProblems2Test {

    private int height(BstProblems2.TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Test
    void insertsRecursively() {
        var root = new BstProblems2.TreeNode(5);
        BstProblems2.insert(root, 3);
        BstProblems2.insert(root, 7);
        BstProblems2.insert(root, 6);
        assertThat(root.left.val).isEqualTo(3);
        assertThat(root.right.val).isEqualTo(7);
        assertThat(root.right.left.val).isEqualTo(6);
    }

    @Test
    void insertsIteratively() {
        var root = new BstProblems2.TreeNode(5);
        BstProblems2.insertIterative(root, 2);
        BstProblems2.insertIterative(root, 8);
        assertThat(root.left.val).isEqualTo(2);
        assertThat(root.right.val).isEqualTo(8);
    }

    @Test
    void buildsBalancedFromSortedArray() {
        int[] arr = {1,2,3,4,5,6,7};
        var root = BstProblems2.buildBalanced(arr);
        assertThat(root.val).isEqualTo(4);
        assertThat(height(root)).isLessThanOrEqualTo(3);
    }
}
