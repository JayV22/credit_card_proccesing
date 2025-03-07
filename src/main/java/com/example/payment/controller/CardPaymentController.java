package com.example.payment.controller;

import com.example.payment.dto.CardPaymentDTO;
import com.example.payment.dto.PaymentResponseDTO;
import com.example.payment.service.CardPaymentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class CardPaymentController {

    private final CardPaymentService cardPaymentService;

    @Autowired
    public CardPaymentController(CardPaymentService cardPaymentService) {
        this.cardPaymentService = cardPaymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody @Valid CardPaymentDTO cardPaymentDTO) {
        PaymentResponseDTO response = cardPaymentService.processPayment(cardPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
