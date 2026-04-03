package leetcode.LC133;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

/*
*
* public class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

*
* */
public class Solution3 extends Solution {
    @Override
    public Node cloneGraph(Node node) {

        if (node == null) return null;

        var visited = new HashSet<Node>();
        var q = new ArrayDeque<Node>();

        var clones = new HashMap<Node, Node>();

        q.offer(node);
        visited.add(node);
        clones.put(node, new Node(node.val));



        while(!q.isEmpty()){
            var proto = q.pop();
            var current = clones.get(proto);

            for(var neighbor: proto.neighbors) {

                if (!clones.containsKey(neighbor)) {
                    clones.put(neighbor, new Node(neighbor.val));
                }
                var n = clones.get(neighbor);
                current.neighbors.add(n);

                if (!visited.contains(neighbor)) {
                    q.offer(neighbor);
                    visited.add(neighbor);
                }
            }


        }
        return clones.get(node);
    }
}
