package dev.expert.interview.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class CustomLruCache6 {

    private class Node {
        public int key, value;
        public Node prev, next;
        public Node(int k, int v) { key = k; value =v;}
    }

    public final Map<Integer, Node> map;
    private final int capacity;

    private final Node head;
    private final Node tail;

    public CustomLruCache6(int capacity) {
        map = HashMap.newHashMap(capacity);
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);

        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        var node = map.get(key);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        add(new Node(key, value));
    }

    //getting & putting are basically the same operation, always re-add it back to the front. putting just checks capacity

    private void add(Node node) {
        if (map.containsKey(node.key)) {
            remove(map.get(node.key));
        }
        if (map.size() >= capacity) {
            remove(tail.prev);
        }

        var prevFirst = head.next;
        prevFirst.prev = node;
        head.next = node;
        node.prev = head;
        node.next = prevFirst;


        map.put(node.key, node);

    }
    private void remove(Node node) {


        node.prev.next = node.next;
        node.next.prev = node.prev;

        map.remove(node.key);

    }

}
