package com.jiyoung.kafkaPayment.platform.representation.request.payment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSettlement {
    private String startDate;
    private String endDate;
    private int page;
    private int size;
}
