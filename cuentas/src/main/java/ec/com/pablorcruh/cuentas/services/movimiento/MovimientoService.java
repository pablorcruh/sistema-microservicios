package ec.com.pablorcruh.cuentas.services.movimiento;

import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;

import java.util.UUID;

public interface MovimientoService {
    MovimientoDTOResponse createMovimiento(UUID cuentaId, MovimientoDTORequest request);
}
