package com.jiyoung.kafkaPayment.platform.application.port.in;


public interface PaymentCancelUseCase {
    boolean paymentCancel(CancelOrder cancelOrder) throws Exception;
}
