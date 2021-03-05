package br.com.douglasffilho.orderstatus.order.listener;

import br.com.douglasffilho.orderstatus.config.RabbitConfig;
import br.com.douglasffilho.orderstatus.model.PaymentChange;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Listeners {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @RabbitListener(queues = RabbitConfig.PAYMENT_UPDATED)
    public void listenToPaymentUpdated(final String message) throws JsonProcessingException {
        PaymentChange change = OBJECT_MAPPER.readValue(message, PaymentChange.class);
        /**
         * TODO
         * fazer service que recebe status de payment e atualiza o status do order
         * se payment reprovado, atualiza o status do order como PAYMENT_REPROVED
         * Fazer um job que lista pedidos reprovados, envia e-mail pro cliente e atualiza o status do pedido como FINISHED
         *
         *
         *
         * se payment aprovado, atualiza o status do order como PAYMENT_APPROVED
         * fazer um job que lista pedidos aprovados e envia mensagem na fila de order.approved
         *
         * shipment ouve order.approved e publica na fila de shipment.on.packaging
         * cria um job no shipment que lista shipments on packaging e publica na fila de shipment.on.delivering
         * cria um job no shipment que lista shipments on delivering e publica na fila de shipment.delivered
         *
         * criar um job no order que ouve a fila de shipment.delivered e envia email pro client e atualiza o status do pedido como FINISHED
         */
    }

}
