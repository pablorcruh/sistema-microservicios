package ec.com.pablorcruh.cuentas;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cuentas")
public record ApplicationProperties(
        String clientesEventsExchange,
        String newClienteQueue
) {}
