package ec.com.pablorcruh.cuentas.services.cuenta;

import ec.com.pablorcruh.cuentas.dtos.request.CuentaDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.CuentaDTOResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CuentaService {

    CuentaDTOResponse createCuenta(CuentaDTORequest request);
    CuentaDTOResponse updateCuenta(UUID id, CuentaDTORequest request);
    void softDeleteCuenta(UUID id);
    Page<CuentaDTOResponse> getAllCuentasActive(int page, int size);
}
