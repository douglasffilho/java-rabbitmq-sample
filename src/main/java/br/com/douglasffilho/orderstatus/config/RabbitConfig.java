package br.com.douglasffilho.orderstatus.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String ORDER_CREATED = "order.created";
    public static final String PAYMENT_UPDATED = "payment.updated";

    @Bean
    public InitializingBean setup(AmqpAdmin amqpAdmin) {
        return () -> {
            setupDirectExchange(ORDER_CREATED, amqpAdmin);
            setupDirectExchange(PAYMENT_UPDATED, amqpAdmin);
        };
    }

    private static void setupDirectExchange(String name, AmqpAdmin amqpAdmin) {
        Exchange exchange = new DirectExchange(name);
        Queue queue = new Queue(name);
        Binding binding = BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(name)
                .noargs();

        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }
}
