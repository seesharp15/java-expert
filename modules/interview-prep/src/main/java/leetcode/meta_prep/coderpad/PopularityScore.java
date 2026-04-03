package leetcode.meta_prep.coderpad;


import java.util.*;

/*
*
* assume a function List<List<String, Int>> getRandomScores() which returns a list of book names to score.
* goal is to return 5 distinct items that have a populatiry score > cutoffValue,
* with their scores in descending order.
*
* getRandomScores() returns a random number of Book->Score mappings.
* The score must be > the cutoffValue.
* i.e.
* getRandomScores(): return 1: List.of(List.of("a", 1"), List.of("b", 1), List.of("c", 1));
* getRandomScores(): return 2: List.of(List.of("a", 1"), List.of("d", 200), List.of("c", 199));
* getRandomScores(): return 3: List.of(List.of("a", 500), List.of("z", 150), List.of(b, 99));
* getRandomScores(): return 3: List.of(List.of("a", 500), List.of("z", 150), List.of(b, 99))
* getRandomScores(): return 3: List.of(List.of("x", 500), List.of("y", 150), List.of(z, 99))
*

 */
public class PopularityScore {

    public List<Map.Entry<String, Integer>> getRandomScores() {return List.of();}
    public List<String> getPopularityScores(int cutoffValue) {

        var map = new HashMap<String, Integer>();
        var completed = new HashSet<String>();

        while(completed.size() < 5) {
            var scores = getRandomScores();
            for(var score: scores) {
                var book = score.getKey();
                var currentScore = map.getOrDefault(book, 0);
                var newScore = currentScore + score.getValue();
                map.put(book, newScore);
                if (newScore > cutoffValue) {
                    completed.add(book);
                }
            }
        }

        var result = new ArrayList<Map.Entry<String, Integer>>();
        for(var c: completed) {
            result.add(Map.entry(c, map.get(c)));
        }
        var x= result.stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).limit(5).map(Map.Entry::getKey);
        return x.toList();
    }
}

//public class PopularityScore2 {
//
//    public List<Map.Entry<String, Integer>> getRandomScores() {return List.of();}
//    public List<String> getPopularityScores(int cutoffValue, int K) {
//        var q = new PriorityQueue<>(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed());
//
//
//        var map = new HashMap<String, Integer>();
//        var completed = new HashSet<String>();
//
//        while(completed.size() < 5) {
//            var scores = getRandomScores();
//            for(var score: scores) {
//                var book = score.getKey();
//                var currentScore = map.getOrDefault(book, 0);
//                var newScore = currentScore + score.getValue();
//                map.put(book, newScore);
//                if (newScore > cutoffValue) {
//                    completed.add(book);
//                }
//            }
//        }
//
//        var result = new ArrayList<Map.Entry<String, Integer>>();
//        for(var c: completed) {
//            result.add(Map.entry(c, map.get(c)));
//        }
//        var x= result.stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).limit(5).map(Map.Entry::getKey);
//        return x.toList();
//    }
//}
