package com.vendorbear.service.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    private final Utils utils = new Utils();

    @Test
    public void testGenerateCreditCardNumber() {
        String generatedCardNumber = this.utils.generateCardNumber();

        assertEquals(16, generatedCardNumber.length(), "Card numbers length must be 16");
    }

    @Test
    public void testGenerateCvc() {
        String generatedCvc = this.utils.generateCvc();

        assertEquals(3, generatedCvc.length(), "Cvc length must be 3");
    }

    @Test
    public void testGenerateReference() {
        String generatedReference = this.utils.generateReference();

        assertNotNull(generatedReference, "Reference can not be null");
    }
}
