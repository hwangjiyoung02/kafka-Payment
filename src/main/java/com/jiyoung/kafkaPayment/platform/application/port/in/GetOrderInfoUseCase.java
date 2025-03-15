package com.jiyoung.kafkaPayment.platform.application.port.in;


import com.jiyoung.kafkaPayment.platform.domain.order.Order;

import java.util.UUID;

public interface GetOrderInfoUseCase {
    Order getOrderInfo(UUID orderId);
}
