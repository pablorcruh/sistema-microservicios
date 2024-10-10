package ec.com.pablorcruh.cliente.repositories;

import ec.com.pablorcruh.cliente.models.ClienteEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEventRepository extends JpaRepository<ClienteEvent, Long> {
}
