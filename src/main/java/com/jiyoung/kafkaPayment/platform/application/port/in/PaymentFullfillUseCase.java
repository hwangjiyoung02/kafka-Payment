package com.jiyoung.kafkaPayment.platform.application.port.in;


import com.jiyoung.kafkaPayment.platform.representation.request.payment.response.PaymentApproved;

import java.io.IOException;

public interface PaymentFullfillUseCase {
    String paymentApproved(PaymentApproved paymentInfo) throws IOException;
}
