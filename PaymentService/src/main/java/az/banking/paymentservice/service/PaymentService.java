package az.banking.paymentservice.service;

import az.banking.paymentservice.core.CustomerServiceClient;
import az.banking.paymentservice.core.PaymentRequest;
import az.banking.paymentservice.model.Payment;
import az.banking.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private  final CustomerServiceClient customerServiceClient;
    private final PaymentRepository paymentRepository;

    public PaymentService(CustomerServiceClient customersClient, PaymentRepository paymentRepository) {
        this.customerServiceClient = customersClient;
        this.paymentRepository = paymentRepository;
    }

    public Payment topUp(PaymentRequest paymentRequest) {
        String pType = "Top-up";
        customerServiceClient.updateCustomerBalance(paymentRequest.getCustomerId(), paymentRequest.getAmount(),pType);

        Payment payment  = new Payment(Long.parseLong(paymentRequest.getCustomerId()), pType, paymentRequest.getAmount());
        return paymentRepository.save(payment);
    }

    public Payment purchase(PaymentRequest paymentRequest) {
        String pType = "Purchase";
        double purchaseAmount = paymentRequest.getAmount();
        customerServiceClient.updateCustomerBalance(paymentRequest.getCustomerId(), purchaseAmount,pType);

        // Create and save the transaction
        Payment payment = new Payment(Long.parseLong(paymentRequest.getCustomerId()), pType, purchaseAmount);
        return paymentRepository.save(payment);
    }

    public Payment refund(PaymentRequest paymentRequest) {
        String pType = "Refund";
        customerServiceClient.updateCustomerBalance(paymentRequest.getCustomerId(), paymentRequest.getAmount(),pType);

        // Create and save the transaction
        Payment payment = new Payment(Long.parseLong(paymentRequest.getCustomerId()), pType, paymentRequest.getAmount());
        return paymentRepository.save(payment);
    }
}