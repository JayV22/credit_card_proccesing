package com.example.payment.bank;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.payment.dto.CardPaymentDTO;

@Component
public class DirectBankAPI {

    /**
     * Simulates processing a transaction directly with a bank.
     * For demo purposes, transactions under $1000 are approved.
     */
    public boolean processTransaction(CardPaymentDTO cardPaymentDTO) {
        // A real implementation would securely transmit the payment details over an SSL/TLS connection
        // to a bank's API and handle encryption, authentication, and error handling.
        BigDecimal threshold = new BigDecimal("1000.00");
        return cardPaymentDTO.getTransactionAmount().compareTo(threshold) < 0;
    }
}
