package ec.com.pablorcruh.cuentas.repositories;

import ec.com.pablorcruh.cuentas.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MovimientoRepository extends JpaRepository<Movimiento, UUID> {

    @Query(value = "select m from Movimiento m where m.cuenta.id = :accountId order by m.date DESC" )
    List<Movimiento> getMovimientosByAccount(@Param(value = "accountId") UUID accountId);
}