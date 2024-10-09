package ec.com.pablorcruh.cuentas.repositories;

import ec.com.pablorcruh.cuentas.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {

    @Query(value = "select c from Cuenta c where c.status = true")
    List<Cuenta> findAllCuentaActive();
}
