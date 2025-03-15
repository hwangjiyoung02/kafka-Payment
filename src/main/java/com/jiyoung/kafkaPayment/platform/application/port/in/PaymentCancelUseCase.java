package com.jiyoung.kafkaPayment.platform.application.port.in;


import com.jiyoung.kafkaPayment.platform.representation.request.order.CancelOrder;

public interface PaymentCancelUseCase {
    boolean paymentCancel(CancelOrder cancelOrder) throws Exception;
}
