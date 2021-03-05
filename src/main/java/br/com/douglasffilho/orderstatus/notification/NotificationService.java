package br.com.douglasffilho.orderstatus.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDirectMessage(final String message, final String directQueue) {
        this.rabbitTemplate.convertAndSend(directQueue, directQueue, message);
    }

}
