package br.com.douglasffilho.orderstatus.payment.service;

import br.com.douglasffilho.orderstatus.config.RabbitConfig;
import br.com.douglasffilho.orderstatus.model.PaymentChange;
import br.com.douglasffilho.orderstatus.notification.NotificationService;
import br.com.douglasffilho.orderstatus.payment.domain.Payment;
import br.com.douglasffilho.orderstatus.payment.domain.PaymentStatus;
import br.com.douglasffilho.orderstatus.payment.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repository;
    private final NotificationService notificationService;

    public PaymentService(final PaymentRepository repository, final NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void create(final String orderId) {
        Payment payment = Payment.initial(orderId);
        this.repository.save(payment);
    }

    public void updateStatus(final String id, final PaymentStatus newStatus) {
        this.repository.findById(id).ifPresent(payment -> {
            payment.setStatus(newStatus);
            this.repository.save(payment);
        });
    }

    public List<Payment> listPayments() {
        return this.repository.findAll();
    }

    @Scheduled(fixedRate = 10_000)
    public void paymentAnalysis() {
        log.info("Processing payments");
        final List<Payment> approved = this.repository.findByStatus(PaymentStatus.APPROVED);
        final List<Payment> reproved = this.repository.findByStatus(PaymentStatus.REPROVED);

        approved.forEach(payment -> {
            PaymentChange change = new PaymentChange(payment.getOrderId(), PaymentStatus.APPROVED.name());
            this.notificationService.sendDirectMessage(change.toString(), RabbitConfig.PAYMENT_UPDATED);
        });

        reproved.forEach(payment -> {
            PaymentChange change = new PaymentChange(payment.getOrderId(), PaymentStatus.REPROVED.name());
            this.notificationService.sendDirectMessage(change.toString(), RabbitConfig.PAYMENT_UPDATED);
        });

        List<Payment> all = new ArrayList<>();
        all.addAll(approved);
        all.addAll(reproved);

        all = all.stream().map(payment -> payment.withStatus(PaymentStatus.FINISHED)).collect(Collectors.toList());
        this.repository.saveAll(all);
    }
}
