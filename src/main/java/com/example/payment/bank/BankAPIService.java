package com.example.payment.bank;

import com.example.payment.dto.CardPaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankAPIService {
    private static final Logger logger = LoggerFactory.getLogger(BankAPIService.class);
    // In production, replace with your bank’s real API endpoint.
    private static final String BANK_API_URL = "https://api.examplebank.com/payments";
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean processPayment(CardPaymentDTO cardPaymentDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CardPaymentDTO> request = new HttpEntity<>(cardPaymentDTO, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(BANK_API_URL, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Bank API processed payment successfully.");
                return true;
            } else {
                logger.error("Bank API declined payment: HTTP {}", response.getStatusCode());
                return false;
            }
        } catch (Exception ex) {
            logger.error("Error calling Bank API: ", ex);
            return false;
        }
    }
}
