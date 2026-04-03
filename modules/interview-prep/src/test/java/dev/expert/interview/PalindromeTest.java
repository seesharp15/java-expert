package dev.expert.interview;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class PalindromeTest {
    //Note: this class pre-existed in the example
    static class Node {
        Node next;
        char val;
        Node(char val) { this.val = val; }
        Node() {}
    }

    @Test
    public void runTest() {
        //Concept here is to answer if words are a palindrome
        //they want you to basically use the node structure versus building a string & doing a reverse match.
        var words = new String[] { "pizza", "other","lionoil", "amanaplanacanalpanama", "racecar", "abcdedcba"};
        for(var word: words) {
            var w = buildNode(word);
            var isPalindrome = isPalindrome(w); //this is the meat of the code challenge.

            System.out.println(String.format("%s: %s", word, isPalindrome));
        }
    }

    private boolean isPalindrome(Node word) {
        // Concept here is to use fast/slow pointers to find the midpoint of the linked list.
        // The fast pointer moves 2 nodes at a time, while the slow pointer moves 1.
        // When fast reaches the end, slow will be at the midpoint.
        //
        // Intuition: if one pointer 2x the speed of the other,
        // it will finish traversing in the same time the slower pointer covers half.
        //
        // For odd-length lists, slow lands on the middle node.
        // For even-length lists, slow lands at the start of the second half.
        //
        // We compare using the second half (after reversing it).
        // If the list has an odd length, the middle node is included in the reversed half,
        // but it does not affect correctness since comparison stops when the second half ends.

        var slow = word;
        var fast = word;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        var secondHalf = reverse(slow);
        while(secondHalf != null) {
            if (word.val != secondHalf.val)
                return false;
            word = word.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    private Node reverse(Node node) {
        var current = node;
        Node prev = null;

        while (current != null) {
            var tmpNext = current.next;
            current.next = prev;
            prev = current;
            current = tmpNext;
        }
        return prev;
    }

    //Note: helper method to build the node for this test, not part of the actual interview
    private static Node buildNode(String word) {
        if (word == null || word.isEmpty()) return new Node();

        var chars = word.toCharArray();
        var node = new Node(chars[0]);

        var current = node;
        for (int i = 1; i < chars.length; i++) {
            var tmp = new Node(chars[i]);
            current.next = tmp;
            current = tmp;
        }
        return node;
    }
}



/*
*
using System;
using Xunit;

namespace Elliott.Interview.Tests
{
    public class PalindromeTest
    {
        // Note: this class pre-existed in the example
        public class Node
        {
            public Node next;
            public char val;

            public Node(char val) { this.val = val; }
            public Node() { }
        }

        [Fact]
        public void RunTest()
        {
            // Concept here is to answer if words are a palindrome
            // they want you to basically use the node structure versus building a string & doing a reverse match.
            var words = new[] { "pizza", "other", "lionoil", "amanaplanacanalpanama", "racecar", "abcdedcba" };

            foreach (var word in words)
            {
                var w = BuildNode(word);
                var isPalindrome = IsPalindrome(w); // this is the meat of the code challenge.

                Console.WriteLine($"{word}: {isPalindrome}");
            }
        }

        private bool IsPalindrome(Node word)
        {
            // Concept here is to use fast/slow pointers to find the midpoint of the linked list.
            // The fast pointer moves 2 nodes at a time, while the slow pointer moves 1.
            // When fast reaches the end, slow will be at the midpoint.
            //
            // Intuition: if one pointer 2x the speed of the other,
            // it will finish traversing in the same time the slower pointer covers half.
            //
            // For odd-length lists, slow lands on the middle node.
            // For even-length lists, slow lands at the start of the second half.
            //
            // We compare using the second half (after reversing it).
            // If the list has an odd length, the middle node is included in the reversed half,
            // but it does not affect correctness since comparison stops when the second half ends.

            var slow = word;
            var fast = word;

            while (fast != null && fast.next != null)
            {
                slow = slow.next;
                fast = fast.next.next;
            }

            var secondHalf = Reverse(slow);

            while (secondHalf != null)
            {
                if (word.val != secondHalf.val)
                    return false;

                word = word.next;
                secondHalf = secondHalf.next;
            }

            return true;
        }

        private Node Reverse(Node node)
        {
            var current = node;
            Node prev = null;

            while (current != null)
            {
                var tmpNext = current.next;
                current.next = prev;
                prev = current;
                current = tmpNext;
            }

            return prev;
        }

        // Note: helper method to build the node for this test, not part of the actual interview
        private static Node BuildNode(string word)
        {
            if (string.IsNullOrEmpty(word)) return new Node();

            var chars = word.ToCharArray();
            var node = new Node(chars[0]);

            var current = node;
            for (int i = 1; i < chars.Length; i++)
            {
                var tmp = new Node(chars[i]);
                current.next = tmp;
                current = tmp;
            }

            return node;
        }
    }
}
* */