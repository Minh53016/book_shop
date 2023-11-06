package com.example.projectbookshop.service.impl;

import com.example.projectbookshop.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        if (amount > 0) {
            return true;
        } else {
            return false;
        }
    }
}
