package br.com.douglasffilho.orderstatus.payment.listener;

import br.com.douglasffilho.orderstatus.config.RabbitConfig;
import br.com.douglasffilho.orderstatus.model.OrderChange;
import br.com.douglasffilho.orderstatus.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Listeners {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private PaymentService service;

    @RabbitListener(queues = RabbitConfig.ORDER_CREATED)
    public void listenToOrderCreated(String message) throws JsonProcessingException {
        OrderChange change = OBJECT_MAPPER.readValue(message, OrderChange.class);

        this.service.create(change.orderId);
    }

}
