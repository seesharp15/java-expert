package leetcode.LC332;

import java.util.*;


public class Solution1 extends Solution {
    @Override
    public List<String> findItinerary(List<List<String>> tickets) {

        var graph = new HashMap<String, PriorityQueue<String>>();

        for(var ticket: tickets){
            graph.computeIfAbsent(ticket.getFirst(), k -> new PriorityQueue<>())
                    .offer(ticket.getLast());

        }

        var result = new ArrayList<String>();
        search("JFK", graph, result);

        return result;
    }

    private void search(String dest, HashMap<String, PriorityQueue<String>> graph, ArrayList<String> result) {
        var nextStops = graph.get(dest);
        while(nextStops != null && !nextStops.isEmpty()) {
            search(nextStops.poll(), graph, result);
        }
        result.addFirst(dest);
    }

    /*
    You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
    Reconstruct the itinerary in order and return it.

    All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

    For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
    */



}
