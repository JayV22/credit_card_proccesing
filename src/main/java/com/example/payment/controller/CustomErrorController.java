package com.example.payment.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        // You can add custom logic here to extract error attributes
        model.addAttribute("error", "An unexpected error occurred.");
        return "error";  // Renders src/main/resources/templates/error.html
    }
}
