package com.example.payment.service;

import com.example.payment.dto.CardPaymentDTO;
import com.example.payment.dto.PaymentResponseDTO;
import com.example.payment.bank.BankAPIService;
import com.example.payment.fraud.FraudDetectionService;
import com.example.payment.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentServiceImpl implements CardPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(CardPaymentServiceImpl.class);

    private final BankAPIService bankAPIService;
    private final FraudDetectionService fraudDetectionService;

    @Autowired
    public CardPaymentServiceImpl(BankAPIService bankAPIService, FraudDetectionService fraudDetectionService) {
        this.bankAPIService = bankAPIService;
        this.fraudDetectionService = fraudDetectionService;
    }

    @Override
    public PaymentResponseDTO processPayment(CardPaymentDTO cardPaymentDTO) {
        try {
            // Encrypt sensitive data (e.g., card number) before logging or storage.
            String encryptedCardNumber = EncryptionUtil.encrypt(cardPaymentDTO.getCardNumber());
            logger.info("Encrypted card number: {}", encryptedCardNumber);
            // (In a PCI DSS-compliant system, never log raw card numbers.)

            // Fraud detection: flag suspicious transactions.
            if (fraudDetectionService.isFraudulent(cardPaymentDTO)) {
                return new PaymentResponseDTO(System.currentTimeMillis(), "declined", "Transaction flagged as fraudulent.");
            }

            // Process payment using real bank API integration.
            boolean approved = bankAPIService.processPayment(cardPaymentDTO);
            Long transactionId = System.currentTimeMillis(); // Simulated transaction ID.
            if (approved) {
                return new PaymentResponseDTO(transactionId, "approved", "Payment processed successfully.");
            } else {
                return new PaymentResponseDTO(transactionId, "declined", "Payment was declined by the bank.");
            }
        } catch (Exception ex) {
            logger.error("Error processing payment: ", ex);
            return new PaymentResponseDTO(System.currentTimeMillis(), "error", "Payment processing error.");
        }
    }
}
