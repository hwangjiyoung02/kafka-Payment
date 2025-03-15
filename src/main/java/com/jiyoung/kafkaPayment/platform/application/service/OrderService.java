package com.jiyoung.kafkaPayment.platform.application.service;

import com.jiyoung.kafkaPayment.platform.application.port.in.CreateNewOrderUseCase;
import com.jiyoung.kafkaPayment.platform.application.port.in.GetOrderInfoUseCase;
import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.infrasturcture.out.jpa.OrderRepository;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements CreateNewOrderUseCase,GetOrderInfoUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(PurchaseOrder newOrder) throws Exception {
        return null;
    }

    @Transactional
    @Override
    public Order getOrderInfo(UUID orderId) {
        return null;
    }
}
