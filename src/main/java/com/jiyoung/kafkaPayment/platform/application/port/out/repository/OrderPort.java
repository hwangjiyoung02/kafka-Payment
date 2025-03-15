package com.jiyoung.kafkaPayment.platform.application.port.out.repository;


import com.jiyoung.kafkaPayment.platform.domain.order.Order;

import java.util.UUID;

public interface OrderPort {
    Order findById(UUID id);
    Order save(Order newOrder);
    boolean removeAll(UUID id);
}
