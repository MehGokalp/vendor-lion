package com.vendorlion.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HelperTest {
    private final Helper helper = new Helper();

    @Test
    public void testGenerateCreditCardNumber() {
        String generatedCardNumber = this.helper.generateCardNumber();

        assertEquals(16, generatedCardNumber.length(), "Card numbers length must be 16");
    }

    @Test
    public void testGenerateCvc() {
        String generatedCvc = this.helper.generateCvc();

        assertEquals(3, generatedCvc.length(), "Cvc length must be 3");
    }

    @Test
    public void testGenerateReference() {
        String generatedReference = this.helper.generateReference();

        assertNotNull(generatedReference, "Reference can not be null");
    }
}
