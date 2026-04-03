package dev.expert.interview.LRUCache;


import java.util.HashMap;
import java.util.Map;

public class CustomLruCache4 {

    private class Node {
        Node next, prev;
        int key, value;
        Node(int k, int v) { key = k; value = v;}
    }
    private final int capacity;

    private final Map<Integer, Node> map;
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);


    public CustomLruCache4(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);

        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        var result = map.get(key);
        remove(result);
        insert(result);
        return result.value;
    }

    public void put(int key, int value) {
        var node = new Node(key, value);
        if (map.containsKey(key)) {
            remove(map.get(key));
        }

        if (map.size() >= capacity) {
            remove(tail.prev);
        }

        insert(node);
    }

    private void remove(Node node) {
        //var node = map.get(key.key);

        var before = node.prev;
        var after = node.next;

        before.next = after;
        after.prev = before;

        map.remove(node.key);
    }

    private void insert(Node value) {
        map.put(value.key, value);

        var second = head.next;
        head.next = value;
        value.next = second;
        second.prev = value;

    }


}
