package ec.com.pablorcruh.cliente.services.cliente;

import ec.com.pablorcruh.cliente.ApplicationProperties;
import ec.com.pablorcruh.cliente.models.ClienteCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClienteEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public ClienteEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void publish(ClienteCreatedEvent event) {
        this.send(properties.newClienteQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.clientesEventsExchange(), routingKey, payload);
    }
}
