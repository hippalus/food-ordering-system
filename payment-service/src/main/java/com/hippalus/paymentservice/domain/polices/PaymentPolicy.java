package com.hippalus.paymentservice.domain.polices;

import com.hippalus.paymentservice.domain.exceptions.InvalidCardException;
import com.hippalus.paymentservice.domain.models.Payment;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

//Workaround solution
public class PaymentPolicy {
    private PaymentPolicy(){
        throw new AssertionError();
    }


    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static void verify(Payment payment) throws InvalidCardException {
        final var constraintViolations = validator.validate(payment);
        close();
        if (!constraintViolations.isEmpty() || !isValidCart(payment.getCardNumber())) {
            throw new InvalidCardException(Payment.class.getName(),
                    PaymentErrorCode.INVALID_CARD_NUMBER,
                    "Invalid Card" + payment.getCardNumber());
        }
    }
    public static boolean isValidCart(String cardNum){
        final var lastValue = cardNum.charAt(cardNum.length() - 1);
        return lastValue%2==0;
    }

    public static void close() {
        factory.close();
    }

}
