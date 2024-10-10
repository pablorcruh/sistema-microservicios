package ec.com.pablorcruh.cliente.jobs;

import ec.com.pablorcruh.cliente.services.cliente.ClienteEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ClienteEventsPublishingJob {

    private static final Logger log = LoggerFactory.getLogger(ClienteEventsPublishingJob.class);

    private final ClienteEventService clienteEventService;

    public ClienteEventsPublishingJob(ClienteEventService clienteEventService) {
        this.clienteEventService = clienteEventService;
    }

    @Scheduled(cron = "${clientes.publish-order-events-job-cron}")
    public void publishOrderEvents() {
        log.info("Publishing Cliente Events at {}", Instant.now());
        clienteEventService.pusblishClienteEvents();
    }

}
