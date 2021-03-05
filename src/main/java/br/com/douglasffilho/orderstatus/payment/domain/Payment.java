package br.com.douglasffilho.orderstatus.payment.domain;

import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private String id;
    private PaymentStatus status;
    private String orderId;

    public Payment() {
        this.id = UUID.randomUUID().toString();
        this.status = PaymentStatus.IDLE;
    }

    public Payment(final PaymentStatus status, final String orderId) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    @Transient
    public Payment withStatus(final PaymentStatus status) {
        this.status = status;
        return this;
    }

    public static Payment initial(final String orderId) {
        return new Payment(PaymentStatus.IDLE, orderId);
    }
}
