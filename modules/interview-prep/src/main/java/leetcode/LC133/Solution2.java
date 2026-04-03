package leetcode.LC133;

public class Solution2 {

    //public Node cloneGraph(Node node) {
    //    if (node == null) return null;
    //
    //    Map<Node, Node> clones = new HashMap<>();
    //    Queue<Node> q = new ArrayDeque<>();
    //    Set<Node> visited = new HashSet<>();
    //
    //    clones.put(node, new Node(node.val));
    //    q.offer(node);
    //    visited.add(node);
    //
    //    while (!q.isEmpty()) {
    //        Node current = q.poll();
    //        Node currentClone = clones.get(current);
    //
    //        for (Node neighbor : current.neighbors) {
    //            if (!clones.containsKey(neighbor)) {
    //                clones.put(neighbor, new Node(neighbor.val));
    //            }
    //
    //            currentClone.neighbors.add(clones.get(neighbor));
    //
    //            if (!visited.contains(neighbor)) {
    //                visited.add(neighbor);
    //                q.offer(neighbor);
    //            }
    //        }
    //    }
    //
    //    return clones.get(node);
    //}

}
