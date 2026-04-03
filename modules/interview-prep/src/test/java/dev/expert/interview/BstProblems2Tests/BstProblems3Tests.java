package dev.expert.interview.BstProblems3Tests;

import dev.expert.interview.BstProblems.BstProblems3;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BstProblems3Tests {


    private int height(BstProblems3.TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Test
    void insertsRecursively() {
        var root = new BstProblems3.TreeNode(5);
        BstProblems3.insert(root, 3);
        BstProblems3.insert(root, 7);
        BstProblems3.insert(root, 6);
        assertThat(root.left.val).isEqualTo(3);
        assertThat(root.right.val).isEqualTo(7);
        assertThat(root.right.left.val).isEqualTo(6);
    }

    @Test
    void insertsIteratively() {
        var root = new BstProblems3.TreeNode(5);
        BstProblems3.insertIterative(root, 2);
        BstProblems3.insertIterative(root, 8);
        assertThat(root.left.val).isEqualTo(2);
        assertThat(root.right.val).isEqualTo(8);
    }

    @Test
    void buildsBalancedFromSortedArray() {
        int[] arr = {1,2,3,4,5,6,7};
        var root = BstProblems3.buildBalanced(arr);
        assertThat(root.val).isEqualTo(4);
        assertThat(height(root)).isLessThanOrEqualTo(3);
    }
}
