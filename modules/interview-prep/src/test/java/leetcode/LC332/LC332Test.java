package leetcode.LC332;


    import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class LC332Test {

    private final Solution solution = new Solution2();

    @Test
    void example1() {
        List<List<String>> tickets = List.of(
                List.of("MUC", "LHR"),
                List.of("JFK", "MUC"),
                List.of("SFO", "SJC"),
                List.of("LHR", "SFO")
        );

        List<String> expected = List.of("JFK", "MUC", "LHR", "SFO", "SJC");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void example2_lexicographicallySmallerPathWins() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "SFO"),
                List.of("JFK", "ATL"),
                List.of("SFO", "ATL"),
                List.of("ATL", "JFK"),
                List.of("ATL", "SFO")
        );

        List<String> expected = List.of("JFK", "ATL", "JFK", "SFO", "ATL", "SFO");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void singleTicket() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "LAX")
        );

        List<String> expected = List.of("JFK", "LAX");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void simpleRoundTrip() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "ATL"),
                List.of("ATL", "JFK")
        );

        List<String> expected = List.of("JFK", "ATL", "JFK");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void duplicateTickets_areBothUsed() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "ATL"),
                List.of("JFK", "ATL"),
                List.of("ATL", "JFK")
        );

        List<String> expected = List.of("JFK", "ATL", "JFK", "ATL");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void mustBacktrack_notGreedyAppend() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "KUL"),
                List.of("JFK", "NRT"),
                List.of("NRT", "JFK")
        );

        List<String> expected = List.of("JFK", "NRT", "JFK", "KUL");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void linearPath() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "A"),
                List.of("A", "B"),
                List.of("B", "C"),
                List.of("C", "D")
        );

        List<String> expected = List.of("JFK", "A", "B", "C", "D");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void cycleWithLexicalChoice() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "B"),
                List.of("JFK", "A"),
                List.of("A", "JFK"),
                List.of("B", "A")
        );

        List<String> expected = List.of("JFK", "A", "JFK", "B", "A");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void repeatedAirportVisits() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "A"),
                List.of("A", "JFK"),
                List.of("JFK", "B"),
                List.of("B", "JFK"),
                List.of("JFK", "C")
        );

        List<String> expected = List.of("JFK", "A", "JFK", "B", "JFK", "C");

        assertEquals(expected, solution.findItinerary(tickets));
    }

    @Test
    void allTicketsMustBeUsed_exactlyOnce() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "A"),
                List.of("A", "C"),
                List.of("C", "JFK"),
                List.of("JFK", "B"),
                List.of("B", "A")
        );

        List<String> expected = List.of("JFK", "A", "C", "JFK", "B", "A");

        assertEquals(expected, solution.findItinerary(tickets));
    }
}