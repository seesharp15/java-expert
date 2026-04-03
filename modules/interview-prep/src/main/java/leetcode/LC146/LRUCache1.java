package leetcode.LC146;


import java.util.HashMap;

public class LRUCache1 extends LRUCache {
    private class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) { this.key = key; this.val = val; }
    }

    private final Node head;
    private final Node tail;

    private final HashMap<Integer, Node> graph;

    public LRUCache1(int capacity) {
        super(capacity);
        graph = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // implement the standard "make most recently used" routine
    // apply it to get & put

    @Override
    public void put(int key, int value) {
        if (graph.containsKey(key)) {
            remove(graph.get(key));
        }
        if (graph.size() >= capacity && tail.prev != head) {
            remove(tail.prev); //last one in the chain
        }

        var node = new Node(key, value);
        add(node);
    }

    @Override
    public int get(int key) {
        if (!graph.containsKey(key)) return -1;
        var node = graph.get(key);
        remove(node);
        add(node);
        return node.val;
    }


    private void remove(Node node) {
        //actually remove from map
        //relink chain
        graph.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void add(Node node){
        //actually add to map
        //link chain. new nodes always go after the head
        graph.put(node.key, node);

        var oldFirst = head.next;
        node.next = oldFirst;
        oldFirst.prev = node;
        head.next = node;
        node.prev = head;
    }

}
