package com.jiyoung.kafkaPayment.platform.application.port.in;


import com.jiyoung.kafkaPayment.platform.domain.payment.PaymentLedger;

import java.util.List;

public interface GetPaymentInfoUseCase {
    List<PaymentLedger> getPaymentInfo(String paymentKey);
    PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey);
}
