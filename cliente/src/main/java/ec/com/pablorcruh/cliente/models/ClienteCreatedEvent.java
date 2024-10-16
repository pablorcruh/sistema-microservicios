package ec.com.pablorcruh.cliente.models;

import java.time.LocalDateTime;

public record ClienteCreatedEvent(
        String eventId,
        String name,
        LocalDateTime createdAt
) {
}
