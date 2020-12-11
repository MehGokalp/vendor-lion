package com.vendorlion.library;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CvcNumberGeneratorTest {

    private final CvcNumberGenerator cvcNumberGenerator = new CvcNumberGenerator();

    @Test
    public void testCvcNumberGeneration() {
        int generated = this.cvcNumberGenerator.generate();

        assertTrue(generated >= 100 && generated < 1000, "Generated cvc number must greater than 100 and lower than 1000.");
    }
}
