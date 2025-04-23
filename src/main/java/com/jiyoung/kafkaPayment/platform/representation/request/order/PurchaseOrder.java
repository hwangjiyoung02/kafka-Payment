package com.jiyoung.kafkaPayment.platform.representation.request.order;

import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.domain.order.OrderItem;
import com.jiyoung.kafkaPayment.platform.domain.order.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @NotNull(message = "The orderer is required.")
    @Valid
    private Orderer orderer;

    @Size(min = 1)
    @Valid
    private List<PurchaseOrderItem> newlyOrderedItem;

    // 도메인 값에 있는 Order에서 OrderItem을 리스트로!-> 꼭 dto랑 도메인 구조가 같지 않아도 됨!
    //1. List<PurchaseOrderItem>에서 각각 객체와 order도메인을 파라미터로 넣어서 OrderItem객체를 builder패턴으로 생상
    public List<OrderItem> convert2OrderItems(Order o) {
        return newlyOrderedItem.stream()
                .map(purchaseOrderItem -> convert2OrderItem(purchaseOrderItem, o))
                .toList();
    }

    private OrderItem convert2OrderItem(PurchaseOrderItem item, Order o) {
        return OrderItem.builder()
                .order(o)
                .itemIdx(item.getItemIdx())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .size("FREE")
                .state(OrderStatus.ORDER_COMPLETED)
                .build();
    }

    public Order toEntity() throws Exception {
        Order o = Order.builder()
                .items(new ArrayList<>())
                .name(this.getOrderer().getName())
                .phoneNumber(this.getOrderer().getPhoneNumber())
                .build();
        o.getItems().addAll(this.convert2OrderItems(o));
//        if (Order.verifyHaveAtLeastOneItem()) throw new Exception("Noting Items");
        o.calculateTotalAmount();
        return o;
    }


}
