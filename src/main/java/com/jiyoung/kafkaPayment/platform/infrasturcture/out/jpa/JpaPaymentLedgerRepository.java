package com.jiyoung.kafkaPayment.platform.infrasturcture.out.jpa;

import com.jiyoung.kafkaPayment.platform.domain.payment.PaymentLedger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaPaymentLedgerRepository extends JpaBaseRepository<PaymentLedger, UUID> {
    Optional<List<PaymentLedger>> findByPaymentKey(String paymentKey);

    Optional<PaymentLedger> findTopByPaymentKeyOrderByIdDesc(String paymentKey);
}
