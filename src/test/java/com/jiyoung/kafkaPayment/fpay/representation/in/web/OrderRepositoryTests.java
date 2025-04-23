package com.jiyoung.kafkaPayment.fpay.representation.in.web;

import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.infrasturcture.out.persistence.repository.order.JpaOrderRepository;
import com.jiyoung.kafkaPayment.platform.representation.request.order.Orderer;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrder;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)// jpa관련 전용 테스트로 테스트 진행시 jpa관련 bean만 띄움
// 운영 db를 사용하지 않고 h2를 사용해서 테스트를  진행할 수 있게 설정해주는 annotation- 데이터 삭제하는 귀찮음을 덜기위해;
public class OrderRepositoryTests {
    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    @Test
    public void save_true_NewOrder() throws Exception {
        // given
        PurchaseOrder newOrder = new PurchaseOrder(
                new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500))
        );
        Order order = newOrder.toEntity();
        // when
        Order result=jpaOrderRepository.save(order);
        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(order);
        // jpaOrderRepository.save();
    }
}
