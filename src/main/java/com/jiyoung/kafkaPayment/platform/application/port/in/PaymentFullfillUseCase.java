package com.jiyoung.kafkaPayment.platform.application.port.in;


import java.io.IOException;

public interface PaymentFullfillUseCase {
    String paymentApproved(PaymentApproved paymentInfo) throws IOException;
}
