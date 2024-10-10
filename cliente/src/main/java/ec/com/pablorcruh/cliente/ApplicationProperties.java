package ec.com.pablorcruh.cliente;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clientes")
public record ApplicationProperties(
        String orderEventsExchange,
        String newClienteQueue
) {
}
