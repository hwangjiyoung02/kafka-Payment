package com.jiyoung.kafkaPayment.platform.representation.request.payment.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
/*
* 결제를 검증하고 승인할때 PG사에서 사용자에서 보내는 요청
* 
* */
@Getter
@RequiredArgsConstructor
public class PaymentApproved {
    private final String paymentType;
    private final String paymentKey;
    private final String orderId;
    private final String amount;
}
