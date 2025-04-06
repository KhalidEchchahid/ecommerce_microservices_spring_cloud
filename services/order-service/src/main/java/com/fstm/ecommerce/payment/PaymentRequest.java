package com.fstm.ecommerce.payment;

import java.math.BigDecimal;

import com.fstm.ecommerce.customer.CustomerResponse;
import com.fstm.ecommerce.order.PaymentMethod;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {
}
