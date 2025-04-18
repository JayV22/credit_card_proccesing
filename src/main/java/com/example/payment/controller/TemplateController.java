package com.example.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @Value("${mercado_pago_sample_public_key}")
    private String mercadoPagoSamplePublicKey;
    
    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoSampleAccessToken;

    @GetMapping
    public String renderMainPage(Model model) {
        model.addAttribute("publicKey", mercadoPagoSamplePublicKey);
        model.addAttribute("accessToken", mercadoPagoSampleAccessToken);
        return "index";
    }
}
