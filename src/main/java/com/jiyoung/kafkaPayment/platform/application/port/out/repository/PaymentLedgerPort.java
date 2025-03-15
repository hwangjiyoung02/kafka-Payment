package com.jiyoung.kafkaPayment.platform.application.port.out.repository;


import com.jiyoung.kafkaPayment.platform.domain.payment.PaymentLedger;

import java.util.List;

public interface PaymentLedgerPort {
    List<PaymentLedger> findAllByPaymentKey(String paymentKey);
    PaymentLedger findOneByPaymentKeyDesc(String paymentKey);
    void save(PaymentLedger paymentLedgerInfo);
    void bulkInsert(List<PaymentLedger> paymentLedgerHistories);
}
