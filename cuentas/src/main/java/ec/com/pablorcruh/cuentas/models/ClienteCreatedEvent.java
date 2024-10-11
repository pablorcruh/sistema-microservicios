package ec.com.pablorcruh.cuentas.models;

import java.time.LocalDateTime;

public record ClienteCreatedEvent(
        String eventId,
        String name,
        LocalDateTime createdAt
) {
}
