package leetcode.LC133;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution4 extends Solution {

    @Override
    public Node cloneGraph(Node node) {

        if (node == null) return null;
        var clones = new HashMap<Node, Node>();
        var q = new ArrayDeque<Node>();
        q.offer(node);
        clones.put(node, new Node(node.val));

        while(!q.isEmpty()) {
            var n = q.poll();
            var clone = clones.get(n);
            if (n.neighbors == null) continue;

            for(var neighbor: n.neighbors) {
                if (!clones.containsKey(neighbor)) {
                    q.offer(neighbor);
                    clones.put(neighbor, new Node(neighbor.val));
                }
                clone.neighbors.add(clones.get(neighbor));
            }
        }

        return clones.get(node);
    }
}
