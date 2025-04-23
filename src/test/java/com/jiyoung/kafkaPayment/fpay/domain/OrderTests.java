package com.jiyoung.kafkaPayment.fpay.domain;

import com.jiyoung.kafkaPayment.platform.domain.order.Order;
import com.jiyoung.kafkaPayment.platform.representation.request.order.CancelOrder;
import com.jiyoung.kafkaPayment.platform.representation.request.order.Orderer;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrder;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrderItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

// 테스트 메서드 명 -> 메소드명_결과값_시나리오
public class OrderTests {

    /**
     * 신규 상품 주문(Purchase Order) 관련 단위 테스트
     * - 상품 주문은 최소 1개 이상 주문해야 한다.
     * [TEST CASE#1] 1개 일 때, return false;
     * [TEST CASE#2] n개 일 때, return false;
     * [Exception] 0개 일 때, 오류 처리
     * 레드 단계-> 실패하도록
     * 그린 -> 내부 구현을 검토하고 수정 -> verifyHaveAtLeastOneItem 메서드 1이상으로 검증
     * 옐로우 -> 불필요한 조건문 제거, 리팩토링
     */
    @Test
    public void verifyHaveAtLeastOneItem_False_ListSizeBiggerThanOne() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(
                new Orderer("황지영", "01048043206"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "독거미", 100000, 2, 200000)
                 ,new PurchaseOrderItem(2, UUID.randomUUID(), "키캡", 200000, 2, 200000)

                ));

        Order order = newOrder.toEntity();
        Assertions.assertFalse(order.verifyHaveAtLeastOneItem());

    }

    // 리스트 사이즈가 0보다 같거나 작다-> false가 나와야함
    @Test
    public void verifyHaveAtLeastOneItem_True_ListSizeZeroOrLess() throws Exception {

        PurchaseOrder newOrder = new PurchaseOrder(
                new Orderer("황지영", "01048043206"),
                Collections.emptyList()
        );

        Order order = newOrder.toEntity();
        Assertions.assertTrue(order.verifyHaveAtLeastOneItem());
    }

    /**
     * 신규 상품 주문(Purchase Order) 관련 단위 테스트
     * - 상품 주문 시, product_id는 중복될 수 없다.
     * [TEST CASE#1] 주문하는 상품의 모든 product_id가 유니크한 경우, return true;
     * [TEST CASE#2] 주문하는 상품의 모든 product_id가 유니크 하지 않을 경우, return false;
     * [Exception] NULL 경우, 오류 처리
     */

    // 하나의 주문에 동일한 상품을 n개 구매할 수 없다
    @Test
    public void verifyDuplicateOrderItemId_True_NotDuplicateProductId() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(
                new Orderer("황지영", "01048043206"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "독거미", 100000, 2, 200000),
                        new PurchaseOrderItem(2, UUID.randomUUID(), "독거미", 100000, 2, 200000)
                ));

        Order order = newOrder.toEntity();
        Assertions.assertTrue(order.verifyDuplicateOrderItemId());
    }

    @Test
    public void verifyDuplicateOrderItemId_ThrowException_DuplicateProductId() throws Exception {
        UUID productId = UUID.randomUUID();
        PurchaseOrder newOrder = new PurchaseOrder(
                new Orderer("황지영", "01048043206"),
                List.of(new PurchaseOrderItem(1, productId, "독거미", 100000, 2, 200000),
                        new PurchaseOrderItem(2, productId, "독거미", 100000, 2, 200000)
                ));
        Order order = newOrder.toEntity();
        Assertions.assertThrows(IllegalArgumentException.class, order::verifyDuplicateOrderItemId);

    }

    /**
     * 결제 완료된 주문(Purchase Order)건에 대한 단위 테스트
     * - "구매 완료" 상태가 아닌 주문 건에 대해서만 취소가 가능하다.
     * [TEST CASE#1] "구매 완료" 상태가 아닌 경우, return true;
     * [TEST CASE#2] "구매 완료" 상태인 경우, return false;
     */
    @DisplayName("[TEST CASE#1] \"구매 완료\" 상태가 아닌 경우, return true;")
    @Test
    public void isNotOrderStatusPurchaseDecision_true_OrderStatusIsNotPurchaseDecision() throws Exception {

    }

    /**
     * 주문 취소 단위 테스트
     * - 상품 정보(itemIdx)가 있는 경우, 부분 취소; 그렇지 않은 전체 취소;
     * [TEST CASE#1] "상품 상세 정보"가 Not Empty 경우, return true;
     * [TEST CASE#2] "상품 상세 정보"가 Empty 경우, return false;
     */
    @DisplayName("[TEST CASE#1] \"상품 상세 정보\"가 Not Empty 경우, return true;")
    @Test
    public void hasItemIdx_true_ItemIdIsNotEmpty() throws Exception {
        UUID orderId = UUID.randomUUID();
        CancelOrder cancelMessage = new CancelOrder(orderId, new int[]{1}, "Reason",
                "tgen_20240605132741Jtkz1", 3400);

        boolean result = cancelMessage.hasItemIdx();
        Assertions.assertTrue(result);
    }

    @DisplayName("[TEST CASE#2] \"상품 상세 정보\"가 Empty 경우, return false;")
    @Test
    public void hasItemIdx_false_ItemIdIsNotEmpty() throws Exception {
        UUID orderId = UUID.randomUUID();
        CancelOrder cancelMessage = new CancelOrder(orderId, new int[]{1}, "Reason",
                "tgen_20240605132741Jtkz1", 3400);

        boolean result = cancelMessage.hasItemIdx();
        Assertions.assertFalse(result);
    }
}
