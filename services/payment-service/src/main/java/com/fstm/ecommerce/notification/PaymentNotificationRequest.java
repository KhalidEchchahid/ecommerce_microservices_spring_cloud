package com.fstm.ecommerce.notification;

import java.math.BigDecimal;

import com.fstm.ecommerce.payment.PaymentMethod;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
