package leetcode.LC133;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class LC133Test {

        private Solution getSolution() {
            return new Solution4();
            
        }

        @Test
        void cloneGraph_nullInput_returnsNull() {
            var solution = getSolution();

            Node cloned = solution.cloneGraph(null);

            assertNull(cloned);
        }

        @Test
        void cloneGraph_singleNode_clonesCorrectly() {
            var solution = getSolution();

            Node node1 = new Node(1);

            Node cloned = solution.cloneGraph(node1);

            assertNotNull(cloned);
            assertNotSame(node1, cloned);
            assertEquals(1, cloned.val);
            assertTrue(cloned.neighbors.isEmpty());
        }

        @Test
        void cloneGraph_twoConnectedNodes_clonesCorrectly() {
            var solution = getSolution();

            Node node1 = new Node(1);
            Node node2 = new Node(2);

            node1.neighbors.add(node2);
            node2.neighbors.add(node1);

            Node cloned = solution.cloneGraph(node1);

            assertGraphEquals(node1, cloned);
            assertDeepCopy(node1, cloned);
        }

        @Test
        void cloneGraph_squareCycle_clonesCorrectly() {
            var solution = getSolution();

            Node node1 = new Node(1);
            Node node2 = new Node(2);
            Node node3 = new Node(3);
            Node node4 = new Node(4);

            node1.neighbors.addAll(List.of(node2, node4));
            node2.neighbors.addAll(List.of(node1, node3));
            node3.neighbors.addAll(List.of(node2, node4));
            node4.neighbors.addAll(List.of(node1, node3));

            Node cloned = solution.cloneGraph(node1);

            assertGraphEquals(node1, cloned);
            assertDeepCopy(node1, cloned);
        }

        @Test
        void cloneGraph_selfLoop_clonesCorrectly() {
            var solution = getSolution();

            Node node1 = new Node(1);
            node1.neighbors.add(node1);

            Node cloned = solution.cloneGraph(node1);

            assertNotNull(cloned);
            assertNotSame(node1, cloned);
            assertEquals(1, cloned.val);
            assertEquals(1, cloned.neighbors.size());
            assertSame(cloned, cloned.neighbors.get(0)); // cloned node points to itself
        }

        @Test
        void cloneGraph_modifyingOriginal_doesNotAffectClone() {
            var solution = getSolution();

            Node node1 = new Node(1);
            Node node2 = new Node(2);

            node1.neighbors.add(node2);
            node2.neighbors.add(node1);

            Node cloned = solution.cloneGraph(node1);

            // mutate original after cloning
            node1.val = 99;
            node1.neighbors.clear();

            // cloned graph should remain unchanged
            assertEquals(1, cloned.val);
            assertEquals(1, cloned.neighbors.size());
            assertEquals(2, cloned.neighbors.get(0).val);
            assertEquals(1, cloned.neighbors.get(0).neighbors.size());
            assertSame(cloned, cloned.neighbors.get(0).neighbors.get(0));
        }

        private void assertGraphEquals(Node original, Node clone) {
            Map<Integer, Set<Integer>> originalGraph = buildAdjacencyMap(original);
            Map<Integer, Set<Integer>> clonedGraph = buildAdjacencyMap(clone);

            assertEquals(originalGraph, clonedGraph);
        }

        private Map<Integer, Set<Integer>> buildAdjacencyMap(Node start) {
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();
            Set<Node> visited = new HashSet<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                Node current = queue.poll();
                graph.putIfAbsent(current.val, new HashSet<>());

                for (Node neighbor : current.neighbors) {
                    graph.get(current.val).add(neighbor.val);
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            return graph;
        }

        private void assertDeepCopy(Node original, Node clone) {
            Queue<Node> q1 = new LinkedList<>();
            Queue<Node> q2 = new LinkedList<>();
            Set<Node> visited1 = new HashSet<>();
            Set<Node> visited2 = new HashSet<>();

            q1.offer(original);
            q2.offer(clone);
            visited1.add(original);
            visited2.add(clone);

            while (!q1.isEmpty()) {
                Node n1 = q1.poll();
                Node n2 = q2.poll();

                assertNotSame(n1, n2);
                assertEquals(n1.val, n2.val);
                assertEquals(n1.neighbors.size(), n2.neighbors.size());

                for (int i = 0; i < n1.neighbors.size(); i++) {
                    Node neighbor1 = n1.neighbors.get(i);
                    Node neighbor2 = n2.neighbors.get(i);

                    assertEquals(neighbor1.val, neighbor2.val);

                    if (!visited1.contains(neighbor1)) {
                        visited1.add(neighbor1);
                        q1.offer(neighbor1);
                    }

                    if (!visited2.contains(neighbor2)) {
                        visited2.add(neighbor2);
                        q2.offer(neighbor2);
                    }
                }
            }
        }
    
}
