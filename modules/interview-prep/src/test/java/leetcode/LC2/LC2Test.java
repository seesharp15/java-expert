package leetcode.LC2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LC2Test {

    private Solution getSolution() {
        return new Solution1(); // swap different attempts here
    }

    @Test
    void example1_342_plus_465_equals_807() {
        // (2 -> 4 -> 3) + (5 -> 6 -> 4) = (7 -> 0 -> 8)
        Solution sol = getSolution();
        ListNode l1 = list(2, 4, 3);
        ListNode l2 = list(5, 6, 4);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{7, 0, 8}, res);
    }

    @Test
    void example2_0_plus_0_equals_0() {
        Solution sol = getSolution();
        ListNode l1 = list(0);
        ListNode l2 = list(0);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{0}, res);
    }

    @Test
    void example3_largeNumbers_withCarryAcrossMultipleDigits() {
        // 9999999 + 9999 = 10009998
        // l1: [9,9,9,9,9,9,9]
        // l2: [9,9,9,9]
        // expected: [8,9,9,9,0,0,0,1]
        Solution sol = getSolution();
        ListNode l1 = list(9, 9, 9, 9, 9, 9, 9);
        ListNode l2 = list(9, 9, 9, 9);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{8, 9, 9, 9, 0, 0, 0, 1}, res);
    }

    @Test
    void differentLengths_noFinalCarry() {
        // 321 + 54 = 375
        // l1: [1,2,3]
        // l2: [4,5]
        // expected: [5,7,3]
        Solution sol = getSolution();
        ListNode l1 = list(1, 2, 3);
        ListNode l2 = list(4, 5);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{5, 7, 3}, res);
    }

    @Test
    void differentLengths_withFinalCarry_newNodeAtEnd() {
        // 99 + 1 = 100
        // l1: [9,9]
        // l2: [1]
        // expected: [0,0,1]
        Solution sol = getSolution();
        ListNode l1 = list(9, 9);
        ListNode l2 = list(1);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{0, 0, 1}, res);
    }

    @Test
    void carryPropagates_throughZeros() {
        // 1000 + 1 = 1001
        // l1: [0,0,0,1]
        // l2: [1]
        // expected: [1,0,0,1]
        Solution sol = getSolution();
        ListNode l1 = list(0, 0, 0, 1);
        ListNode l2 = list(1);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{1, 0, 0, 1}, res);
    }

    @Test
    void manyCarries_allNines_plus_one() {
        // 99999 + 1 = 100000
        // expected: [0,0,0,0,0,1]
        Solution sol = getSolution();
        ListNode l1 = list(9, 9, 9, 9, 9);
        ListNode l2 = list(1);

        ListNode res = sol.addTwoNumbers(l1, l2);

        assertListEquals(new int[]{0, 0, 0, 0, 0, 1}, res);
    }

    @Test
    void inputsShouldNotBeModified_structureAndValuesRemain() {
        // Ensure solution doesn't mutate l1/l2 nodes (common bug: reusing nodes incorrectly)
        Solution sol = getSolution();
        ListNode l1 = list(2, 4, 3);
        ListNode l2 = list(5, 6, 4);

        List<Integer> l1Before = toDigits(l1);
        List<Integer> l2Before = toDigits(l2);

        ListNode res = sol.addTwoNumbers(l1, l2);
        assertListEquals(new int[]{7, 0, 8}, res);

        assertEquals(l1Before, toDigits(l1), "l1 was modified");
        assertEquals(l2Before, toDigits(l2), "l2 was modified");
    }

    @Test
    void resultDigitsAreAlwaysZeroToNine() {
        // Defensive test: verifies you’re handling carry properly, never producing digits like 12.
        Solution sol = getSolution();
        ListNode l1 = list(9, 9, 9);
        ListNode l2 = list(9, 9, 9);

        ListNode res = sol.addTwoNumbers(l1, l2);

        for (int d : toDigits(res)) {
            assertTrue(d >= 0 && d <= 9, "Digit out of range: " + d);
        }
    }


    // ---------- Helpers ----------
    private static ListNode list(int... digits) {
        if (digits == null || digits.length == 0) return null;
        ListNode head = new ListNode(digits[0]);
        ListNode cur = head;
        for (int i = 1; i < digits.length; i++) {
            cur.next = new ListNode(digits[i]);
            cur = cur.next;
        }
        return head;
    }

    private static List<Integer> toDigits(ListNode node) {
        List<Integer> out = new ArrayList<>();
        while (node != null) {
            out.add(node.val);
            node = node.next;
        }
        return out;
    }

    private static void assertListEquals(int[] expectedDigits, ListNode actual) {
        List<Integer> actualDigits = toDigits(actual);
        assertEquals(expectedDigits.length, actualDigits.size(),
                "Length mismatch. Actual digits: " + actualDigits);

        for (int i = 0; i < expectedDigits.length; i++) {
            assertEquals(expectedDigits[i], actualDigits.get(i),
                    "Mismatch at index " + i + ". Actual digits: " + actualDigits);
        }
    }

}
