package gotham.asset.mgmt.GAM08;

public abstract class Solution {

    /**
     * GAM08 - The Modulo Trap
     *
     * <p>DEBUGGING CHALLENGE: This FizzBuzz variant has TWO bugs. Fix them both.</p>
     *
     * <p>For numbers 1 to n (inclusive), build a comma-separated string:</p>
     * <ul>
     *   <li>If divisible by 3 AND 5: "FizzBuzz"</li>
     *   <li>If divisible by only 3: "Fizz"</li>
     *   <li>If divisible by only 5: "Buzz"</li>
     *   <li>Otherwise: the number itself</li>
     * </ul>
     *
     * <p>Expected for n=5: "1,2,Fizz,4,Buzz"</p>
     * <p>Expected for n=15: "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz"</p>
     *
     * <p>Original (buggy):</p>
     * <pre>
     *   public String fizzBuzz(int n) {
     *       StringBuilder sb = new StringBuilder();
     *       for (int i = 0; i < n; i++) {
     *           if (i % 3 == 0) sb.append("Fizz");
     *           else if (i % 5 == 0) sb.append("Buzz");
     *           else if (i % 15 == 0) sb.append("FizzBuzz");
     *           else sb.append(i);
     *           if (i < n - 1) sb.append(",");
     *       }
     *       return sb.toString();
     *   }
     * </pre>
     *
     * <p>Fix both bugs and return the correct comma-separated string.
     * Return an empty string for n <= 0.</p>
     */
    public abstract String fizzBuzz(int n);
}
