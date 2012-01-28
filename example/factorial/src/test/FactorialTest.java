package test;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FactorialTest {

    /**
     * Method that performs the mathematical factorial
     * operation (n!) for values up to 10!
     *
     * @param n
     * @return n!
     */
    private int factorial(int n) {
        if (n < 2) {
            return 1;
        }

       return factorial(n-1)*n;
    }


    @Test
    public void shouldReturnOneWhenZeroIn() {
        assertEquals(1, factorial(0));
    }

    @Test
    public void shouldReturnOneWhenOneIn() {
        assertEquals(1, factorial(1));
    }

    @Test
    public void shouldReturnTwo() {
        assertEquals(2, factorial(2));
    }

    @Test
    public void shouldReturnSix() {
        assertEquals(6, factorial(3));
    }

    @Test
    public void shouldReturnTwentyFour() {
        assertEquals(24, factorial(4));
    }

    @Test
    public void shouldFindCorrectFactorialFor10() {
        assertEquals(3628800, factorial(10));
    }

    @Test
    public void shouldReturnCorrectFactorialValue() {
        int values[][] = {{0,1}, {1,1}, {2,2}, {3,6}, {4,24}, {10, 3628800}};
        for(int[] value: values) {
            assertEquals(value[1], factorial(value[0]));
        }
    }

}
