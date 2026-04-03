package leetcode.LC133;

//https://leetcode.com/problems/clone-graph/description

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class Solution1 extends Solution {

    @Override
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Queue<Node> q = new ArrayDeque<>();
        var clones = new HashMap<Node, Node>();
        var visited = new HashSet<Node>();
        q.offer(node);
        visited.add(node);
        clones.put(node, new Node(node.val));


        while(!q.isEmpty()) {
            var current = q.poll();
            var currentClone = clones.get(current);
            for (var neighbor : current.neighbors) {
                if (!clones.containsKey(neighbor)) {
                    clones.put(neighbor, new Node(neighbor.val));
                }

                currentClone.neighbors.add(clones.get(neighbor));

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.offer(neighbor);
                }

            }
        }

        return clones.get(node);
    }
}
