package com.vendorlion.service.card;

import com.vendorlion.library.CreditCardNumberGenerator;
import com.vendorlion.library.CvcNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {
    private final CreditCardNumberGenerator creditCardNumberGenerator = new CreditCardNumberGenerator();
    private final CvcNumberGenerator cvcNumberGenerator = new CvcNumberGenerator();

    public String generateCardNumber() {
        return this.creditCardNumberGenerator.generate("4948", 16);
    }

    public String generateCvc() {
        return Integer.toString(this.cvcNumberGenerator.generate());
    }

    public String generateReference() {
        return UUID.randomUUID().toString();
    }
}
