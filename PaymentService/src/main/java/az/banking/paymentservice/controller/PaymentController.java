package az.banking.paymentservice.controller;

import az.banking.paymentservice.core.PaymentRequest;
import az.banking.paymentservice.model.Payment;
import az.banking.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/topup")
    public ResponseEntity<Payment> topUp(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.topUp(paymentRequest);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Payment> purchase(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.purchase(paymentRequest);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/refund")
    public ResponseEntity<Payment> refund(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.refund(paymentRequest);
        return ResponseEntity.ok(payment);
    }
}
