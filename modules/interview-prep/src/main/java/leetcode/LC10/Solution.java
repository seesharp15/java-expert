package leetcode.LC10;

/*
* 10. Regular Expression Matching
Hard


Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.
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
public class Solution {
    public boolean isMatch(String s, String p) {
        var target = s.toCharArray();
        var pattern = p.toCharArray();

       // if (pattern.length > target.length) return false;

        return matches(target, pattern, 0, 0, null);

    }

    private static boolean matches(char[] targetChars, char[] patternChars, int targetIndex, int patternIndex, Character previousPattern) {
//        if (patternIndex >= patternChars.length) //targetIndex >= targetChars.length ||
//            return targetIndex >= targetChars.length;

        //if below is correct, we shouldn't be in a "valid" case where we

        //no more chars to check
            //pattern must also be at the end or in a *

        if (targetIndex >= targetChars.length && patternIndex >= patternChars.length) return true;
        //if (targetIndex >= targetChars.length || patternIndex >= patternChars.length) return false;

       // if (targetIndex >= targetChars.length || patternIndex >= patternChars.length) return false;

        //if (patternIndex >= patternChars.length) return false;

        var current = targetIndex >= targetChars.length ? Character.MIN_VALUE :  targetChars[targetIndex];
        var pattern = patternIndex >= patternChars.length ? Character.MAX_VALUE : patternChars[patternIndex]; //patternIndex >= patternChars.length ? null : patternChars[patternIndex];

        if (patternIndex < patternChars.length - 1 && patternChars[patternIndex+1] == '*') {
//            if (pattern == '.') {
//                //(i.e. we're in a .*, so zero or matches of anything
//                return matches(targetChars, patternChars, targetIndex + 1, patternIndex, pattern);
//            }
//            else if (current == pattern) {
//                //i.e. we're in a x*, so zero or matches of x
//                return matches(targetChars, patternChars,targetIndex + 1, patternIndex, pattern);
//            }
        //targetIndex >= targetChars.length ? null : targetChars[targetIndex];


            if (pattern == '.' || current == pattern) {
                if (targetIndex + 1 >= targetChars.length) {
                    return matches(targetChars, patternChars,targetIndex + 1, patternIndex + 2, pattern);
                    //&& patternIndex + 2 < patternChars.length-1) return true;
                } else {
                    return matches(targetChars, patternChars,targetIndex + 1, patternIndex, pattern);
                }

            }
            else {
                //zero match case, fine but move both forward
                return matches(targetChars, patternChars, targetIndex, patternIndex + 2, pattern);
            }

        } else {
            //if (current == null || pattern != current) return false;

            if (pattern != current && pattern != '.') return false;
            return matches(targetChars, patternChars, targetIndex + 1, patternIndex + 1, pattern);
        }
        //

//        else if (pattern == '*') {
//            throw new RuntimeException("This should not happen");
//            //based on the above we shouldn't get here.
//           // if (current == null) return true;
//
////            if (previousPattern == '.' || previousPattern == current) {
////                return matches(targetChars, patternChars, targetIndex + 1, patternIndex, previousPattern);
////                //match
////                //don't move pattern until no match, just increment current and check again
////            }
////            else { //zero matches (still ok, but move it forward)
////                return matches(targetChars, patternChars, targetIndex + 1, patternIndex + 2, pattern);
////
////            }
//        } else if (pattern == '.') {
//            if (patternIndex < patternChars.length - 1 && patternChars[patternIndex+1] == '*') {
//                return matches(targetChars, patternChars, targetIndex, patternIndex + 1, pattern);
//            } else { //at end of the pattern, so as long as there's a char in target, we're good
//                //return current != null;
//                return matches(targetChars, patternChars, targetIndex + 1, patternIndex + 1, pattern);
//            }
//            //look ahead, if asterix, move pattern forward so the above handles it
//            //otherwise, there needs to be a value, so move both forward
//        } else {
//            //if (current == null || pattern != current) return false;
//            return matches(targetChars, patternChars, targetIndex + 1, patternIndex + 1, pattern);
//
//        }

    }

}
