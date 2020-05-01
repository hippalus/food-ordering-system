package com.hippalus.paymentservice.application.service;

import com.hippalus.paymentservice.application.request.PaymentRequest;
import com.hippalus.paymentservice.application.response.PaymentResponse;
import com.hippalus.paymentservice.domain.commands.PayCommand;
import com.hippalus.paymentservice.domain.domainevents.IDomainEventPublisher;
import com.hippalus.paymentservice.domain.exceptions.InvalidCardException;
import com.hippalus.paymentservice.domain.models.*;
import com.hippalus.paymentservice.domain.polices.PaymentPolicy;
import com.hippalus.paymentservice.domain.repository.PaymentRepository;
import com.hippalus.paymentservice.domain.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository repository;
    private final IDomainEventPublisher eventPublisher;

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public PaymentResponse pay(PaymentRequest request) {
        var cmd = requestToCommand(request);
        var payment = Payment.pay(cmd);
        try {
            PaymentPolicy.verify(payment);
            repository.saveAndFlush(payment);
            payment.success();
            publishEvent(payment);
        } catch (InvalidCardException e) {
            payment.failed();
            publishEvent(payment);
        }
        return new PaymentResponse(payment.getStatus().toString());
    }

    private void publishEvent(Payment payment) {
        eventPublisher.publishEvent(payment.getDomainEvents());
    }


    private PayCommand requestToCommand(PaymentRequest request) {

        final var transactionItems = request.getItems()
                .stream()
                .map(trxItm -> TransactionItem.builder()
                        .id(new TransactionItemId(trxItm.getId()))
                        .itemDescription(trxItm.getItemDescription())
                        .price(trxItm.getPrice())
                        .quantity(trxItm.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return PayCommand.builder()
                .cardNumber(request.getCardNumber())
                .paymentMethod(PaymentMethod.of(request.getPaymentMethod()))
                .transaction(new Transaction(new TransactionId(request.getId()), transactionItems))
                .build();

    }
}
