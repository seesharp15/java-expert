package gotham.asset.mgmt.GAM05;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM05Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testBasic() {
        assertEquals("abcdef", getSolution().mangle("abcdef"));
    }

    @Test
    void testOddLength() {
        assertEquals("abcde", getSolution().mangle("abcde"));
    }

    @Test
    void testSingleChar() {
        assertEquals("x", getSolution().mangle("x"));
    }

    @Test
    void testTwoChars() {
        assertEquals("ab", getSolution().mangle("ab"));
    }

    @Test
    void testEmptyString() {
        assertEquals("", getSolution().mangle(""));
    }

    @Test
    void testPalindrome() {
        assertEquals("racecar", getSolution().mangle("racecar"));
    }

    @Test
    void testLongString() {
        assertEquals("HelloWorld123", getSolution().mangle("HelloWorld123"));
    }
}
