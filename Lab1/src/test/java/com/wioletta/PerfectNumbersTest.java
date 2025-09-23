package com.wioletta;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerfectNumbersTest {

    @Test
    public void testPerfectNumbers() {
        assertTrue(PerfectNumbers.isPerfect(6), "6 має бути досконалим числом");
        assertTrue(PerfectNumbers.isPerfect(28), "28 має бути досконалим числом");
        assertTrue(PerfectNumbers.isPerfect(496), "496 має бути досконалим числом");
    }

    @Test
    public void testNonPerfectNumbers() {
        assertFalse(PerfectNumbers.isPerfect(1), "1 не є досконалим числом");
        assertFalse(PerfectNumbers.isPerfect(10), "10 не є досконалим числом");
        assertFalse(PerfectNumbers.isPerfect(12), "12 не є досконалим числом");
    }

    @Test
    public void testEdgeCases() {
        assertFalse(PerfectNumbers.isPerfect(0), "0 не є досконалим числом");
        assertFalse(PerfectNumbers.isPerfect(-6), "Від'ємні числа не є досконалими");
    }
}