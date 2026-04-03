package leetcode.meta_prep.LC146;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache

 Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

 Implement the LRUCache class:

 LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 int get(int key) Return the value of the key if the key exists, otherwise return -1.
 void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 The functions get and put must each run in O(1) average time complexity.


 */


public class Solution1 extends Solution {
    static class Node {
        private int key, val;
        private Node prev, next;
        Node(int k, int v) { key = k; val = v; }
    }

    private final Map<Integer, Node> map;
    private final int capacity;

    private final Node head;
    private final Node tail;

    public Solution1(int capacity) {
        super(capacity);
        map = new HashMap<>(capacity);
        this.capacity = capacity;

        head = new Node(-1, 0);
        tail = new Node(-2, 0);

        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            map.remove(key);
        }

        if (map.size() >= capacity) {
            var pk = tail.prev.key;
            remove(tail.prev);
            map.remove(pk);
        }

        var newNode = new Node(key, value);
        map.put(key, newNode);
        add(newNode);
    }

    @Override
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        var node = map.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    private void remove(Node node) {
        var p = node.prev;
        var n = node.next;

        p.next = n;
        n.prev = p;
//
//        node.prev = node.next;
//        node.prev.next = node.next;
//        node.next.prev = node.prev;
    }

    private void add(Node node) {
        var prevFirst = head.next;



        head.next = node;
        node.prev = head;
        prevFirst.prev = node;
        node.next = prevFirst;

    }
}
