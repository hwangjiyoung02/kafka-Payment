package com.jiyoung.kafkaPayment.platform.infrasturcture.repository;

import com.jiyoung.kafkaPayment.platform.application.port.out.repository.PaymentLedgerPort;
import com.jiyoung.kafkaPayment.platform.domain.payment.PaymentLedger;
import com.jiyoung.kafkaPayment.platform.infrasturcture.out.jpa.JpaPaymentLedgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.sql.PreparedStatement;

/*
 * PaymentLedgerPort를 구현하는 구현체 *
 *  */
@Repository
@RequiredArgsConstructor
public class PaymentRepository implements PaymentLedgerPort {

    private final JpaPaymentLedgerRepository jpaPaymentRepository; //생성자 주입을 통해 초기화
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<PaymentLedger> findAllByPaymentKey(String paymentKey) {
        return jpaPaymentRepository.findByPaymentKey(paymentKey)
                .orElseThrow(() -> new NullPointerException("findAllByPaymentKey not found"));
    }

    @Override
    public PaymentLedger findOneByPaymentKeyDesc(String paymentKey) {
        return jpaPaymentRepository.findTopByPaymentKeyOrderByIdDesc(paymentKey)
                .orElseThrow(() -> new NullPointerException("findOneByPaymentKeyDesc not found"));
    }

    @Override
    public void save(PaymentLedger paymentLedgerInfo) {
        jpaPaymentRepository.save(paymentLedgerInfo);
    }

    @Override
    public void bulkInsert(List<PaymentLedger> paymentLedgerHistories) {
        String sqlStatement = "INSERT INTO payment_transaction (payment_id, method, payment_Status, total_amount, balance_amount, canceled_amount, pay_out_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
                sqlStatement,
                paymentLedgerHistories,
                paymentLedgerHistories.size(),
                (PreparedStatement ps, PaymentLedger data)->{
                    ps.setString(1,data.getPaymentKey());
                    ps.setString(2,data.getMethod().toString());
                    ps.setString(3,data.getPaymentKey());
                    ps.setInt(4,data.getTotalAmount());
                    ps.setInt(5,data.getBalanceAmount());
                    ps.setInt(6,data.getCanceledAmount());
                    ps.setInt(7,data.getPayOutAmount());
                }
        );


    }
}
