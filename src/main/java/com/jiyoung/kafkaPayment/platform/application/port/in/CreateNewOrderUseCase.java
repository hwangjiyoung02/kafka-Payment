package com.jiyoung.kafkaPayment.platform.application.port.in;


import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrder;

public interface CreateNewOrderUseCase {
    Order createOrder(PurchaseOrder newOrder) throws Exception;
}
