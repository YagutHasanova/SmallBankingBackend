package az.banking.customerservice.service;

import az.banking.customerservice.model.Customer;
import az.banking.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        customer.setBalance(100);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setSurname(updatedCustomer.getSurname());
            customer.setBirthDate(updatedCustomer.getBirthDate());
            customer.setGsmNumber(updatedCustomer.getGsmNumber());
            customer.setBalance(updatedCustomer.getBalance());
            return customerRepository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean updateCustomerBalance(String customerId, double amount, String pType){
        Optional<Customer>  existingCustomer = customerRepository.findById(Long.parseLong(customerId));
        if (existingCustomer != null) {
            if (existingCustomer.isPresent()) {
                Customer customer = existingCustomer.get();
                double balance = customer.getBalance();

                switch(pType) {
                    case "Top-up":
                        balance+= amount;
                        break;
                    case "Purchase":
                        balance-= amount;
                        break;
                    case "Refund":
                        balance+= amount/3.0;
                        break;
                    default:

                }
                customer.setBalance(balance);
                customerRepository.save(customer);
                return  true;
            }
        }
        return  false;
    }
}
