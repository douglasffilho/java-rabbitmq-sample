package br.com.douglasffilho.orderstatus.order.service;

import br.com.douglasffilho.orderstatus.notification.NotificationService;
import br.com.douglasffilho.orderstatus.order.domain.Order;
import br.com.douglasffilho.orderstatus.order.domain.OrderStatus;
import br.com.douglasffilho.orderstatus.model.OrderChange;
import br.com.douglasffilho.orderstatus.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.douglasffilho.orderstatus.config.RabbitConfig.ORDER_CREATED;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final NotificationService notificationService;

    public OrderService(final OrderRepository repository, final NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Order create(Order order) {
        Order created = this.repository.save(order);

        OrderChange change = new OrderChange(created.getId());
        this.notificationService.sendDirectMessage(change.toString(), ORDER_CREATED);

        return created;
    }

    public void updateStatus(final String id, final OrderStatus newStatus) {
        this.repository.findById(id).ifPresent(order -> {
            order.setStatus(newStatus);
            this.repository.save(order);
        });
    }

    public List<Order> listOrders() {
        return this.repository.findAll();
    }
}
