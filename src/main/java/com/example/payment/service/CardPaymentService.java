package com.example.payment.service;

import com.example.payment.dto.CardPaymentDTO;
import com.example.payment.dto.PaymentResponseDTO;

public interface CardPaymentService {
    PaymentResponseDTO processPayment(CardPaymentDTO cardPaymentDTO);
}
