package gotham.asset.mgmt.GAM05;

public abstract class Solution {

    /**
     * GAM05 - String Builder Deception
     *
     * <p>What does this method return for input "abcdef"?</p>
     * <pre>
     *   public String mangle(String s) {
     *       StringBuilder sb = new StringBuilder(s);
     *       for (int i = 0; i < sb.length() / 2; i++) {
     *           char c = sb.charAt(i);
     *           sb.setCharAt(i, sb.charAt(sb.length() - 1 - i));
     *           sb.setCharAt(sb.length() - 1 - i, c);
     *       }
     *       sb.reverse();
     *       return sb.toString();
     *   }
     * </pre>
     *
     * <p>Implement a method that returns the same result as the above for any input string.</p>
     */
    public abstract String mangle(String s);
}
