package leetcode.LC433;

/*
\A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation
is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene.
If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.

Example 1:

Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1
Example 2:

Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2

    * */

import javax.swing.tree.DefaultTreeCellEditor;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

public class Solution1 extends Solution {

    private static char[] bases = new char[] {'G', 'A', 'T', 'C'};

    @Override
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene == null || endGene == null || bank == null || bank.length == 0) return -1;

        var dict = new HashSet<String>();
        for(var gene: bank) {
            if (gene == null) continue;
            dict.add(gene);
        }

        if (startGene.equals(endGene)) return 0;
        if (!dict.contains(endGene)) return -1;


        //for each gene, find every possible mutation, if it's a valid gene, queue it up for processing. return the first match
        var q = new ArrayDeque<String>();
        q.offer(startGene);

        var steps = 0;
        while (!q.isEmpty()) {
            //process the queue in "waves"
            var size = q.size();
            for(var i = 0; i < size; i++) {
                var gene = q.poll();
                if (gene == null) break;
                //for every base in the gene, rotate it by 1 (get all permutations)
                    //check if it's valid, if valid, queue it up, remove it from list of work (dict);
                var chars = gene.toCharArray();
                for(var c = 0; c<chars.length;c++) {
                    var original = chars[c];
                    for (char base : bases) {
                        chars[c] = base;
                        var newGene = new String(chars);
                        if (newGene.equals(endGene)) return steps + 1;
                        if (dict.remove(newGene)) {
                            q.offer(newGene);
                        }
                    }
                    chars[c] = original;
                }
            }
            steps++;
        }

        return -1;
    }
}
