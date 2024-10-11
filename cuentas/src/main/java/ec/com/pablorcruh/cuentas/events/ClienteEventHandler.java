package ec.com.pablorcruh.cuentas.events;

import ec.com.pablorcruh.cuentas.models.ClienteCreatedEvent;
import ec.com.pablorcruh.cuentas.models.ClienteEvent;
import ec.com.pablorcruh.cuentas.repositories.ClienteEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ClienteEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ClienteEventHandler.class);

    private final ClienteEventRepository clienteEventRepository;

    public ClienteEventHandler(ClienteEventRepository clienteEventRepository) {
        this.clienteEventRepository = clienteEventRepository;
    }

    @RabbitListener(queues = "${cuentas.new-cliente-queue}")
    public void handle(ClienteCreatedEvent event) {
        if (clienteEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate ClienteCreatedEvent with eventId: {}", event.eventId());
            return;
        }
        var clienteEvent = new ClienteEvent(event.eventId(),event.name(),event.createdAt());
        clienteEventRepository.save(clienteEvent);
    }
}
