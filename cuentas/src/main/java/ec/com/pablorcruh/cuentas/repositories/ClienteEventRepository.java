package ec.com.pablorcruh.cuentas.repositories;

import ec.com.pablorcruh.cuentas.models.ClienteEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEventRepository extends JpaRepository<ClienteEvent, Long> {
    boolean existsByEventId(String eventId);
    boolean existsByPayload(String clienteName);
}
