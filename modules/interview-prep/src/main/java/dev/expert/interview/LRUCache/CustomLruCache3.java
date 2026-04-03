package dev.expert.interview.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class CustomLruCache3 {

    class Node {
        Node prev, next;
        int key,value;
        Node(int k, int v) {key=k;value=v;}
    }
    private final int capacity;

    private final Map<Integer, Node> map;

    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0,0);

    public CustomLruCache3(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));
        //remove if exists
        //evict if necessary;
        //re-add
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        var node = map.get(key);
        remove(node);
        insert(node);
        return node.value;
    }

    private void remove(Node node) {
        map.remove(node.key);

        //fill in gap
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void insert(Node node) {

        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;

        head.next = node;
        node.prev = head;
    }

}
