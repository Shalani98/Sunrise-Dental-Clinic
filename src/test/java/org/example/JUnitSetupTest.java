package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JUnitSetupTest {

    @Test
    void junitShouldRunSuccessfully() {
        int actualResult = 2 + 2;

        assertEquals(4, actualResult);
    }
}