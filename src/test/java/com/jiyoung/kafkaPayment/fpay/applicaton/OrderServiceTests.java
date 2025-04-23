package com.jiyoung.kafkaPayment.fpay.applicaton;

import com.jiyoung.kafkaPayment.platform.application.service.OrderService;
import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.infrasturcture.out.persistence.repository.order.OrderRepository;
import com.jiyoung.kafkaPayment.platform.representation.request.order.Orderer;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrder;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    /*
    * 목객체를 mockito(객체는 정의하지만 빈값임)를 사용해서 일련의 비지니스로직을 적용하고 잘되는지 테스트
    * 영속성은 상관하지 않고 Order를 return하는지 확인
    * */

    /*
    *  상품 주문은 최소 1개 이상
    *  한 번의 주문에 다수의 상품을 n개 구매할 수 있다
    *  하나의 주문에 동일한 상품을 n개 구매할 수 없다
    *  결제 수단은 카드 결제만 가능하며 추후 추가될 수 있음
    *  주문 완료된 주문 건에 대해서만 결제를 진행할 수 있다.    *
    * */

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void createOrder_NewOrder_ANormalOrderForm() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        // 어떻게 동작하는지 -> when
        Mockito.when(orderRepository.save(any()))
            .thenReturn(order);

        Order completedOrder = orderService.createOrder(newOrder);

        // mockito가 실행되었는지 확인
        Mockito.verify(orderRepository, Mockito.times(1)).save(any());
        Assertions.assertEquals(order, completedOrder);
    }

}
