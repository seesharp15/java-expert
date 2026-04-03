package leetcode.LC332;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solution2 extends Solution {


    @Override
    public List<String> findItinerary(List<List<String>> tickets) {
        var q = new HashMap<String, PriorityQueue<String>>();

        for(var ticket: tickets){
            var from = ticket.getFirst();
            var to = ticket.getLast();
            q.computeIfAbsent(from, x -> new PriorityQueue<>())
                    .offer(to);
        }

        var result = new ArrayList<String>();
        search("JFK", result, q);

        return result;
    }

    private void search(String from, ArrayList<String> result, HashMap<String, PriorityQueue<String>> q) {
        var destQ = q.get(from);

        while(destQ != null && !destQ.isEmpty()) {
            search(destQ.poll(), result, q);
        }
        result.addFirst(from);
    }
}
