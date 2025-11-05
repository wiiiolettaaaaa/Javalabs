package com.wioletta;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PerfectNumbersTest {

    @Test
    void testIsPerfectTrue() {
        assertTrue(PerfectNumbers.isPerfect(6));
        assertTrue(PerfectNumbers.isPerfect(28));
        assertTrue(PerfectNumbers.isPerfect(496));
    }

    @Test
    void testIsPerfectFalse() {
        assertFalse(PerfectNumbers.isPerfect(1));
        assertFalse(PerfectNumbers.isPerfect(12));
        assertFalse(PerfectNumbers.isPerfect(100));
        assertFalse(PerfectNumbers.isPerfect(500));
    }

    @Test
    void testIsPerfectZeroNegative() {
        assertFalse(PerfectNumbers.isPerfect(0));
        assertFalse(PerfectNumbers.isPerfect(-10));
    }

    @Test
    void testPerfectNumbersInRangeTo1000() {
        List<Integer> perfect = IntStream.rangeClosed(1, 1000)
                .filter(PerfectNumbers::isPerfect)
                .boxed()
                .toList();

        assertEquals(List.of(6, 28, 496), perfect);
    }
}