package com.jiyoung.kafkaPayment.platform.representation.in.web;

import com.jiyoung.kafkaPayment.platform.application.port.in.CreateNewOrderUseCase;
import com.jiyoung.kafkaPayment.platform.application.port.in.GetOrderInfoUseCase;
import com.jiyoung.kafkaPayment.platform.representation.request.order.PurchaseOrder;
import com.jiyoung.kafkaPayment.platform.representation.response.NewPurchaseOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final CreateNewOrderUseCase createNewOrderUseCase;
    private final GetOrderInfoUseCase getOrderInfoUseCase;

    // 주문 생성하기
    @PostMapping("/new")
    public NewPurchaseOrder newOrder(@RequestBody @Valid PurchaseOrder newOrder) throws Exception{
        // 도메인을 -> dto로 변환하는 건 from
        return NewPurchaseOrder.from(createNewOrderUseCase.createOrder(newOrder));
    }

    // 주문 번호로 상세 항목 조회하기
    @GetMapping("query")
    public NewPurchaseOrder getOrderById(@RequestParam(value="order_id") UUID orderId){
        return NewPurchaseOrder.from(getOrderInfoUseCase.getOrderInfo(orderId));
    }

    // 유저 이름을 반환
//    @GetMapping("/info")
//    public Map<String,String> requestParams(@RequestParam (value="username") String username){
//        Map<String,String> params=new HashMap<>();
//        params.put("username",username);
//        return params;
//    }

//    @GetMapping("query")
//    public NewPurchaseOrder getOrderById(@RequestParam(value = "order_id") UUID orderId){
//        return NewPurchaseOrder.from(getOrderInfoUseCase.getOrderInfo(orderId));
//    }
}
