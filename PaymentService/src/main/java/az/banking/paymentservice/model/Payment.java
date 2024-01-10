package az.banking.paymentservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private double amount;

    private Long customerId;


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date paymentDT;

    public Payment() {
    }

    public Payment(Long customerId, String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.customerId = customerId;
    }
}
