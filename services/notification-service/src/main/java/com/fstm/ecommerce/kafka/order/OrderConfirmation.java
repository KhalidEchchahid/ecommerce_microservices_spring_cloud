package com.fstm.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.fstm.ecommerce.kafka.payment.PaymentMethod;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
