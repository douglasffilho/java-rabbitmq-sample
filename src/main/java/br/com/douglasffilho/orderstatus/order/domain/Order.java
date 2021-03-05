package br.com.douglasffilho.orderstatus.order.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;
    private OrderStatus status;
    private String identifier;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.CREATED;
    }

    public Order(OrderStatus status, String identifier) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.identifier = identifier;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }
}
