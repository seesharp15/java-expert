package gotham.asset.mgmt.GAM04.answerkey;

import gotham.asset.mgmt.GAM04.Solution;

import java.util.Iterator;
import java.util.Map;

/**
 * GAM04 Answer - HashMap Mutation During Iteration
 *
 * The enhanced for-each loop uses an iterator internally. Calling map.remove()
 * directly (rather than through the iterator) structurally modifies the map
 * while the iterator is active, triggering ConcurrentModificationException.
 *
 * Fix: use Iterator.remove() to safely remove entries during iteration.
 */
public class Answer extends Solution {

    @Override
    public int sumPositive(Map<String, Integer> map) {
        int sum = 0;
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() < 0) {
                it.remove();
            } else {
                sum += entry.getValue();
            }
        }
        return sum;
    }
}
