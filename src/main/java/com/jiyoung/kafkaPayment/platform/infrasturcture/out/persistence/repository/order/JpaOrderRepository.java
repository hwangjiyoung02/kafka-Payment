package com.jiyoung.kafkaPayment.platform.infrasturcture.out.persistence.repository.order;

import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.infrasturcture.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/*
 * 단순히 DB CRUD 기능만을 담당하는 infra 계층
 * */
@Repository
public interface JpaOrderRepository extends JpaBaseRepository<Order, UUID> {

}
