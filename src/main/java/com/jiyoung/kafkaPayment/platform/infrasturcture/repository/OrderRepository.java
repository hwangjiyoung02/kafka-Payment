package com.jiyoung.kafkaPayment.platform.infrasturcture.repository;

import com.jiyoung.kafkaPayment.platform.application.port.out.repository.OrderPort;
import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.infrasturcture.out.jpa.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.UUID;

/*
 * 비지니스 로직과 연관된 데이터 접근 로직
 * 유지보수성을 높이기 위해 사용
 * 원래 application port out에 interface로 추상화해야함-> 간결하게 구성하기 위해 제거
 * */
@Repository
@RequiredArgsConstructor
public class OrderRepository implements OrderPort {

    private final JpaOrderRepository jpaOrderRepository; //생성자 주입을 통해 초기화
    
    public Order findById(UUID id) {
        return jpaOrderRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("OrderId not found"));
    }

    public Order save(Order newOrder) {
        return jpaOrderRepository.save(newOrder);
    }

    public boolean removeAll(UUID id) {
        return jpaOrderRepository.deleteById(id);
    }

}
