package dev.expert.interview.LRUCache;


import java.util.HashMap;
import java.util.Map;

/**
 * Problem 1: Implement an LRU cache with O(1) get/put. Follow-up: make thread-safe.
 */
public class CustomLruCache7{
    private final int capacity;

    // TODO: choose an O(1) data structure (likely LinkedHashMap or DLL+HashMap)

    private class Node {
        Node prev, next;
        int key, val;
        Node(int k, int v) { key = k; val = v; }

    }
    private final Map<Integer, Node> nodeMap;

    private final Node first;
    private final Node last;

    public CustomLruCache7(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        this.capacity = capacity;

        nodeMap = new HashMap<>(capacity);
        first = new Node(0, 0);
        last = new Node(0, 0);

        //initial relationship
        first.next = last;
        last.prev = first;

    }

    // intuitively we know that getting/putting always result in that item being the last used
    // so, getting always needs to "touch" and get the item
    //     putting always needs to "touch" and put the item
                // we need historical tracking here because of eviction (after cap fill).

    //     we will implement a single way to "touch" the item, thereby making it the last item used

    /**
     * Return value for key, or -1 if missing. Should mark as most recently used.
     */
    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;

        var node = nodeMap.get(key);
        remove(node);

        var newNode = add(node.key, node.val);
        return newNode.val;
    }

    /**
     * Insert or update value for key. Evict least recently used when over capacity.
     */
    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            remove(nodeMap.get(key));
        }

        if (nodeMap.size() >= capacity) {
            remove(last.prev);
        }

        add(key, value);
    }

    /** Optional: return current size (for tests/debugging). */
    public int size() {
        return nodeMap.size();
    }

    private Node add(int key, int value) {
        var node = new Node(key, value);

        var prevMostRecent = first.next;

        prevMostRecent.prev = node;
        first.next = node;

        node.next = prevMostRecent;
        node.prev = first;

        nodeMap.put(node.key, node);
        return node;
    }

    private void remove(Node node) {
        //update the node before this node to point to the node after this node, and vise versa
        var before = node.prev;
        var after = node.next;

        before.next = after;
        after.prev = before;

        nodeMap.remove(node.key);
    }


}
