package com.fstm.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.fstm.ecommerce.customer.CustomerResponse;
import com.fstm.ecommerce.order.PaymentMethod;
import com.fstm.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
