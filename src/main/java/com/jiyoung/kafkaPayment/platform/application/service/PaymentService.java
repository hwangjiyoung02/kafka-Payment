package com.jiyoung.kafkaPayment.platform.application.service;


import com.jiyoung.kafkaPayment.platform.application.port.in.*;
import com.jiyoung.kafkaPayment.platform.domain.payment.PaymentLedger;
import com.jiyoung.kafkaPayment.platform.infrasturcture.repository.OrderRepository;
import com.jiyoung.kafkaPayment.platform.infrasturcture.repository.PaymentRepository;
import com.jiyoung.kafkaPayment.platform.representation.request.payment.response.PaymentApproved;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements GetPaymentInfoUseCase, PaymentFullfillUseCase {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
//    private final Set<TransactionTypeRepository> transactionTypeRepositorySet;
//    private final PaymentAPIs tossPayment;

    @PostConstruct
    public void init() {

    }
    //
    @Transactional
    @Override
    public String paymentApproved(PaymentApproved paymentInfo) throws IOException {

        return paymentRepository.
    }

    @Override
    public List<PaymentLedger> getPaymentInfo(String paymentKey) {
        return paymentRepository.findAllByPaymentKey(paymentKey);
    }

    @Override
    public PaymentLedger getLatestPaymentInfoOnlyOne(String paymentKey) {
        return paymentRepository.findOneByPaymentKeyDesc(paymentKey);
    }


}
