package ec.com.pablorcruh.cliente.services.cliente;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.pablorcruh.cliente.enums.ClienteEventType;
import ec.com.pablorcruh.cliente.models.ClienteCreatedEvent;
import ec.com.pablorcruh.cliente.models.ClienteEvent;
import ec.com.pablorcruh.cliente.repositories.ClienteEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteEventService {

    private static final Logger log = LoggerFactory.getLogger(ClienteEventService.class);

    private final ClienteEventRepository clienteEventRepository;

    private final ClienteEventPublisher clienteEventPublisher;

    private final ObjectMapper objectMapper;

    public ClienteEventService(ClienteEventRepository clienteEventRepository, ClienteEventPublisher clienteEventPublisher, ObjectMapper objectMapper) {
        this.clienteEventRepository = clienteEventRepository;
        this.clienteEventPublisher = clienteEventPublisher;
        this.objectMapper = objectMapper;
    }


    void save(ClienteCreatedEvent event){
        ClienteEvent clienteEvent = new ClienteEvent();
        clienteEvent.setEventId(event.eventId());
        clienteEvent.setEventType(ClienteEventType.CLIENTE_CREATED);
        clienteEvent.setCreatedAt(event.createdAt());
        clienteEvent.setPayload(toJsonPayload(event));
        this.clienteEventRepository.save(clienteEvent);
    }

    public void pusblishClienteEvents(){
        Sort sort = Sort.by("createdAt").ascending();
        List<ClienteEvent> events = this.clienteEventRepository.findAll(sort);
        log.info("Found {} Client Events to be published", events.size());
        for (ClienteEvent event : events) {
            this.publishEvent(event);
            clienteEventRepository.delete(event);
        }
    }

    private void publishEvent(ClienteEvent event) {
        ClienteEventType eventType = event.getEventType();
        switch (eventType) {
            case CLIENTE_CREATED:
                ClienteCreatedEvent orderCreatedEvent = fromJsonPayload(event.getPayload(), ClienteCreatedEvent.class);
                clienteEventPublisher.publish(orderCreatedEvent);
                break;
            default:
                log.warn("Unsupported ClienteEventType: {}", eventType);
        }
    }

    private String toJsonPayload(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T fromJsonPayload(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
