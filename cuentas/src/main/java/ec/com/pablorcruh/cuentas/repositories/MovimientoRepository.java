package ec.com.pablorcruh.cuentas.repositories;

import ec.com.pablorcruh.cuentas.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimientoRepository extends JpaRepository<Movimiento, UUID> {
}
