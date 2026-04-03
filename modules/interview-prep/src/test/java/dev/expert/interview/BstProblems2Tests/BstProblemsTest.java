package dev.expert.interview.BstProblems2Tests;

import dev.expert.interview.BstProblems.BstProblems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BstProblemsTest {

    private int height(BstProblems.TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Test
    void insertsRecursively() {
        var root = new BstProblems.TreeNode(5);
        BstProblems.insert(root, 3);
        BstProblems.insert(root, 7);
        BstProblems.insert(root, 6);
        assertThat(root.left.val).isEqualTo(3);
        assertThat(root.right.val).isEqualTo(7);
        assertThat(root.right.left.val).isEqualTo(6);
    }

    @Test
    void insertsIteratively() {
        var root = new BstProblems.TreeNode(5);
        BstProblems.insertIterative(root, 2);
        BstProblems.insertIterative(root, 8);
        assertThat(root.left.val).isEqualTo(2);
        assertThat(root.right.val).isEqualTo(8);
    }

    @Test
    void buildsBalancedFromSortedArray() {
        int[] arr = {1,2,3,4,5,6,7};
        var root = BstProblems.buildBalanced(arr);
        assertThat(root.val).isEqualTo(4);
        assertThat(height(root)).isLessThanOrEqualTo(3);
    }
}
