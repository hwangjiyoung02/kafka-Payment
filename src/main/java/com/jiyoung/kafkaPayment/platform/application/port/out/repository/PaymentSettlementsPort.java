package com.jiyoung.kafkaPayment.platform.application.port.out.repository;


import com.jiyoung.kafkaPayment.platform.domain.settlements.PaymentSettlements;

import java.util.List;

public interface PaymentSettlementsPort {
    PaymentSettlements findById(String paymentKey);
    void bulkInsert(List<PaymentSettlements> paymentSettlements);
}
