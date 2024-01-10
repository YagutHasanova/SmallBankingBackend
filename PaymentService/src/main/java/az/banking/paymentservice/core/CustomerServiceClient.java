package az.banking.paymentservice.core;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "CustomerService", url = "http://localhost:8081/api")
public interface CustomerServiceClient {

    @PostMapping("/customers/updateBalance")
    void updateCustomerBalance(@RequestParam("customerId") String customerId, @RequestParam("amount") double amount, @RequestParam("pType") String pType);

}