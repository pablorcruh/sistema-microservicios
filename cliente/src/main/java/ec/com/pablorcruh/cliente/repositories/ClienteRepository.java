package ec.com.pablorcruh.cliente.repositories;

import ec.com.pablorcruh.cliente.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    @Query(value = "select c from Cliente c where c.status = true")
    List<Cliente> findAllActive();
}
