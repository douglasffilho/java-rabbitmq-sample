package br.com.douglasffilho.orderstatus.payment.repository;

import br.com.douglasffilho.orderstatus.payment.domain.Payment;
import br.com.douglasffilho.orderstatus.payment.domain.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByStatus(final PaymentStatus status);
}
