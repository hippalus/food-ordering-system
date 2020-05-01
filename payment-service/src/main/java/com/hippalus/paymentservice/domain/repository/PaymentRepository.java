package com.hippalus.paymentservice.domain.repository;

import com.hippalus.paymentservice.domain.models.Payment;
import com.hippalus.paymentservice.domain.models.PaymentId;
import com.hippalus.sharedkernel.infrastructure.jpa.IJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends IJpaRepository<Payment, PaymentId> {
}
