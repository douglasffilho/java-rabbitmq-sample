package br.com.douglasffilho.orderstatus.payment.controller;

import br.com.douglasffilho.orderstatus.payment.domain.Payment;
import br.com.douglasffilho.orderstatus.payment.domain.PaymentStatus;
import br.com.douglasffilho.orderstatus.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStatus(
            @PathVariable final String id,
            @RequestParam(defaultValue = "IDLE", required = false) final String status
    ) {
        PaymentStatus newStatus = PaymentStatus.valueOf(status);
        this.service.updateStatus(id, newStatus);

        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Payment>> listAll() {
        List<Payment> payments = this.service.listPayments();
        return ResponseEntity.ok(payments);
    }
}
