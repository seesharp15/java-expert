package gotham.asset.mgmt.GAM08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM08Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testOne() {
        assertEquals("1", getSolution().fizzBuzz(1));
    }

    @Test
    void testUpToFive() {
        assertEquals("1,2,Fizz,4,Buzz", getSolution().fizzBuzz(5));
    }

    @Test
    void testUpToFifteen() {
        assertEquals("1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz",
                getSolution().fizzBuzz(15));
    }

    @Test
    void testFizzBuzzAt30() {
        String result = getSolution().fizzBuzz(30);
        assertTrue(result.endsWith("FizzBuzz"));
    }

    @Test
    void testUpToThree() {
        assertEquals("1,2,Fizz", getSolution().fizzBuzz(3));
    }

    @Test
    void testZero() {
        assertEquals("", getSolution().fizzBuzz(0));
    }

    @Test
    void testNegative() {
        assertEquals("", getSolution().fizzBuzz(-5));
    }

    @Test
    void testUpToSix() {
        assertEquals("1,2,Fizz,4,Buzz,Fizz", getSolution().fizzBuzz(6));
    }
}
