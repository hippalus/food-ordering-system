package com.hippalus.paymentservice.domain.models;

import com.hippalus.paymentservice.domain.commands.PayCommand;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PaymentTest {

    @Test
    void test() {
        //given:
        var item = TransactionItem.builder()
                .itemDescription("Kebap")
                .id(new TransactionItemId("asda232-sdksl2"))
                .price(BigDecimal.valueOf(30))
                .quantity(3)
                .build();
        List<TransactionItem> items = new ArrayList<>();
        items.add(item);

        var cmd = PayCommand.builder()
                .cardNumber("5105105105105100")
                .paymentMethod(PaymentMethod.CREDITCARD)
                .transaction(new Transaction(new TransactionId("123"), items))
                .build();
        //when:
        final var payment = Payment.pay(cmd);

        //then:
        assertThat(payment).isNotNull();
    }


}