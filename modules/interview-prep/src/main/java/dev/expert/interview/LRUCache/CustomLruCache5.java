package dev.expert.interview.LRUCache;


import java.util.HashMap;
import java.util.Map;

public class CustomLruCache5 {

    private class Node {
        Node after, before;
        int key,value;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    public final HashMap<Integer, Node> nodeMap;
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public CustomLruCache5(int capacity) {
        this.capacity = capacity;
        nodeMap = HashMap.newHashMap(capacity);

        head.after = tail;
        tail.before = head;
    }



    public synchronized int get(int key){
        //"visiting" the item makes it LRU
        //canonical way to do this is to just re-insert the item as if it were new
        if (!nodeMap.containsKey(key)) return -1;
        var node = nodeMap.get(key);
        remove(node);
        insert(node);
        return node.value;
    }
    public synchronized void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            remove(nodeMap.get(key));
        }

        if (nodeMap.size() >= capacity) {
            remove(tail.before);
        }

        insert(new Node(key, value));

        //putting or updating item makes it LRU
        //if exists, remove
        //make sure not at capacity
        //re-insert as new
    }

    private void remove(Node node) {
        //link node's before item to node's after item (and vice versa)
        //remove node from map
        nodeMap.remove(node.key);
        node.before.after = node.after;
        node.after.before = node.before;



//
//        var before = node.before;
//        var after = node.after;
//
//        before.after = after;
//        after.before = before;
    }
    private void insert(Node node) {

        nodeMap.put(node.key, node);

        head.after.before = node;
        node.before = head;
        node.after = head.after;

        head.after = node;



        //link node after head to after this one
        //link this node to head
        //add to map
    }

}
