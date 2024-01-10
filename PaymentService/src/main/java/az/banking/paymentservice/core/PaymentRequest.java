package az.banking.paymentservice.core;

import lombok.Data;

@Data
public class PaymentRequest {
    private String customerId;
    private double amount;
}
