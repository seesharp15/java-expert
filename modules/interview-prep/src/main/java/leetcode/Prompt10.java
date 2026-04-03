package leetcode;


/*
* Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
Return a boolean indicating whether the matching covers the entire input string (not partial).


Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".


Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
* */

import java.util.HashSet;

public class Prompt10 {
    private class MatchMemo {
        private final int inputPosition;
        private final int patternPosition;
        private final boolean isMatch;

        public MatchMemo(int inputPosition, int patternPosition, boolean isMatch) {
            this.inputPosition = inputPosition;
            this.patternPosition = patternPosition;
            this.isMatch = isMatch;
        }
    }
    public boolean isMatch(String s, String p) {
        HashSet<MatchMemo> memo = HashSet.newHashSet(s.length());
        return validateChar(s.toCharArray(), p.toCharArray(), 0, 0,  memo);
    }

    private static boolean validateChar(char[] input, char[] pattern, int inputPosition, int patternPosition, HashSet<MatchMemo> memo) {
        if (inputPosition > input.length - 1) return true; //pastEndOfInput
        if (patternPosition > pattern.length - 1) return false; //pastEndOfPattern

        var patChar = pattern[patternPosition];
        var inputChar = input[inputPosition];

        if (patternPosition < pattern.length - 1 && pattern[patternPosition + 1] == '*') {
            if (patChar == inputChar || patChar == '.') { //matches input, move on in input but don't move on in pattern (until it doesn't match)
                var isNextValid = validateChar(input, pattern, inputPosition + 1, patternPosition, memo);
                if (patternPosition + 2 >= pattern.length - 1) //if at the end of the pattern, just return a match
                {
                    return isNextValid;
                } else {
                    var isLookAheadValid = validateChar(input, pattern, inputPosition, patternPosition + 2, memo); //zero instance case
                    return isNextValid || isLookAheadValid;
                }
            } else {
                return validateChar(input, pattern, inputPosition + 1, patternPosition + 2, memo);
            }

        } else if (patChar == '.' || inputChar == patChar) {

            if (inputPosition >= input.length - 1) return true;
            return validateChar(input, pattern, inputPosition + 1, patternPosition + 1, memo);
        }
        return false;
    }
}



//        var sa = s.toCharArray();
//        var pa = p.toCharArray();
//
//        var pp = 0;
//        var sp = 0;
//        var lastKnownChar = '.';
//        //for(var sp = 0;sp<s.length();sp++) {
//        while(sp < sa.length) {
//            if (pp == pa.length) return false;
//
//            if(pa[pp] == '.') {
//                sp ++;
//                pp ++;
//            }
//            else if (pa[pp] == '*') {
//                if (lastKnownChar == '.' || sa[sp] == lastKnownChar) {
//                    sp++;
//                    //don't update the pattern position yet
//                    continue;
//                } else if ( sp+1 < sa.length && pp+1 < pa.length && sa[sp + 1] == pa[pp+1])  {
//                    sp++;
//                    pp++;
//                    continue;
//
//                }
//                return false;
//
//
//            } else if (pa[pp] != sa[sp]) {
//                return false;
//            } else {
//                lastKnownChar = sa[sp];
//                sp ++;
//                pp ++;
//            }
//
//
//        }
//
//        return true;
