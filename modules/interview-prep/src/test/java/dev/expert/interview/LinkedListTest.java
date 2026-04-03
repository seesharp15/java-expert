package dev.expert.interview;

 
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LinkedListTest {

    static class Node {
        Node next;
        int val;
        Node(int val) { this.val = val; }
    }
    
    private static Node buildNode(int n) {
        var node = new Node(0);
        var current = node;
        for (int i = 1; i < n; i++) {
            var tmp = new Node(i);
            current.next = tmp;
            current = tmp;
        }
        return node;
    }

    private static void printNode(Node node) {
        var current = node;

        while(current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    private static Node reverseNode(Node node) {

        var current = node;

        var root = new Node(node.val);
        Node prev = null;

        while(current != null) {
            var tmp = current.next;
            root.next = new Node(prev.val);
            current.next = prev;
            prev = current;
            current = tmp;
        }
        return prev;
    }

    @Test
    void reverseList() {
        var node = buildNode(10);
        var reversed = reverseNode(node);


        printNode(reversed);


    }
}
