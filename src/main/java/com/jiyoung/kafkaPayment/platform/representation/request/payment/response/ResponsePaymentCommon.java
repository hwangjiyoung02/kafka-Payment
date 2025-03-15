package com.jiyoung.kafkaPayment.platform.representation.request.payment.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ResponsePaymentCommon {
    private String orderId;
    private String paymentKey;
    private String method;
    private String status;
    private int totalAmount;
    private int balanceAmount;

}
