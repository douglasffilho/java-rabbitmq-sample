package br.com.douglasffilho.orderstatus.order.repository;

import br.com.douglasffilho.orderstatus.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
