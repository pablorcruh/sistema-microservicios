package ec.com.pablorcruh.cuentas.services.movimiento;

import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoByDateDTOResponse;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MovimientoService {
    MovimientoDTOResponse createMovimiento(UUID cuentaId, MovimientoDTORequest request);

    Page<MovimientoByDateDTOResponse> filterMovimientoByDate(
            Date startDate,
            Date endDate,
            UUID accountId,
            int page,
            int size);
}
