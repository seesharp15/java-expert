package dev.expert.interview.TopKFrequent;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class TopKFrequent2 {
    /**
     * Return k most frequent words, frequency desc, lexicographic asc for ties.
     */
    public static List<String> topK(List<String> words, int k) {

        var freqMap = new TreeMap<String, Integer>();

        for(var word: words) {
            freqMap.merge(word, 1, Integer::sum);
//            if (freqMap.containsKey(word)) {
//                freqMap.get(
//            }
        }


        return freqMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();


    }

}
