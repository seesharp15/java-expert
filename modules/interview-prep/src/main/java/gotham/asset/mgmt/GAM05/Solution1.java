package gotham.asset.mgmt.GAM05;

public class Solution1 extends Solution {


    //Solution
    //GAM05 - String Builder Deception
    //What does this method return for input "abcdef"?
    //    public String mangle(String s) {
    //        StringBuilder sb = new StringBuilder(s);
    //        for (int i = 0; i < sb.length() / 2; i++) {
    //            char c = sb.charAt(i);
    //            sb.setCharAt(i, sb.charAt(sb.length() - 1 - i));
    //            sb.setCharAt(sb.length() - 1 - i, c);
    //        }
    //        sb.reverse();
    //        return sb.toString();
    //    }
    //

    //Implement a method that returns the same result as the above for any input string.


    //original method is just reversing the string then reversing it back
        //I provided a cleaned up version & then the "answer" version.
        // However, I'd argue that the implementation is bad here because the
            // method does not have the effect that the name implies, and likely would
            // not approve this code based on that alone, regardless of implementation.


    public String mangleInternal(String s) {
        var sb = new StringBuilder(s);
        var builderLength = sb.length();

        for (int i = 0; i < sb.length() / 2; i++) {
            var compliment =  builderLength - 1 - i;
            swapChars(sb, i, compliment);
        }

        //"abcdef" -> "fedcba" -> "abcdef";
        sb.reverse();
        return sb.toString();
    }

    @Override
    public String mangle(String s) {
        return s;
    }

    private void swapChars(StringBuilder s, int a, int b) {
        var tmp = s.charAt(a);
        s.setCharAt(a, s.charAt(b));
        s.setCharAt(b, tmp);
    }
}
