package leetcode.meta_prep.LC146;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 */
public class Solution {
    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0,0);
    private final Node tail = new Node(0,0);

    public Solution(int capacity) {
        this.capacity = capacity;
        head.next = tail; tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node fresh = new Node(key, value);
            insertFront(fresh);
            map.put(key, fresh);
        }
    }

    // ----- helpers -----
    private void moveToFront(Node n) { remove(n); insertFront(n); }
    private void insertFront(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }
    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private static class Node {
        int key, val; Node prev, next;
        Node(int k, int v){ key=k; val=v; }
    }
}
