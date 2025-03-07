package com.example.payment.fraud;

import com.example.payment.dto.CardPaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudDetectionService {
    private static final Logger logger = LoggerFactory.getLogger(FraudDetectionService.class);
    // Example threshold—transactions above $5000 trigger a fraud alert.
    private static final BigDecimal FRAUD_THRESHOLD = new BigDecimal("5000.00");

    public boolean isFraudulent(CardPaymentDTO cardPaymentDTO) {
        if (cardPaymentDTO.getTransactionAmount().compareTo(FRAUD_THRESHOLD) > 0) {
            logger.warn("Potential fraud detected: Transaction amount {} exceeds threshold.", 
                        cardPaymentDTO.getTransactionAmount());
            return true;
        }
        return false;
    }
}
