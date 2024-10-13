package ec.com.pablorcruh.cuentas.repositories;

import ec.com.pablorcruh.cuentas.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MovimientoRepository extends JpaRepository<Movimiento, UUID> {

    @Query(value = "select m from Movimiento m where m.cuenta.id = :accountId order by m.date DESC" )
    List<Movimiento> getMovimientosByAccount(@Param(value = "accountId") UUID accountId);

    @Query(value = "select m from Movimiento m where m.date between :startDate and :endDate and m.cuenta.id = :accountId order by m.date DESC ")
    List<Movimiento> getMovimientosByDate(
            @Param(value = "startDate") Date startDate,
            @Param(value = "endDate") Date endDate,
            @Param(value = "accountId") UUID accountId);
}