package dev.expert.interview.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class CustomLruCache2 {

    private final int capacity;

    class Node {
        Node next, prev;
        int key, value;
        private Node(int k, int v) {key = k; value = v;}
    }

    private final Map<Integer, Node> map;
    private final Node head = new Node(0,0);
    private final Node tail = new Node(0,0);


    public CustomLruCache2(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            remove(node);
        }

        if (map.size() >= capacity) {
            map.remove(tail.prev);
        }
        add(new Node(key, value));
    }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        var node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    private void remove(Node node) {
        map.remove(node);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void add(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }



}
