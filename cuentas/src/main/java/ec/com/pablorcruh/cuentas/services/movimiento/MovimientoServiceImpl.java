package ec.com.pablorcruh.cuentas.services.movimiento;

import ec.com.pablorcruh.cuentas.dtos.converters.MovimientoConverter;
import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.exceptions.NotFoundException;
import ec.com.pablorcruh.cuentas.models.Cuenta;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import ec.com.pablorcruh.cuentas.repositories.CuentaRepository;
import ec.com.pablorcruh.cuentas.repositories.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;

    private final CuentaRepository cuentaRepository;

    private final MovimientoConverter movimientoConverter;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository, MovimientoConverter movimientoConverter) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoConverter = movimientoConverter;
    }

    @Override
    public MovimientoDTOResponse createMovimiento(UUID cuentaId, MovimientoDTORequest request) {
        Cuenta cuenta = findCuentayId(cuentaId);
        if(cuenta == null){
            throw new NotFoundException(String.format("Movimiento with id %s no found", cuentaId));
        }
        Movimiento movimiento = movimientoConverter.toEntity(request);
        movimiento.setDate(new Date());
        movimiento.setCuenta(cuenta);
        movimiento.setMovementType(request.getMovementType());
        Movimiento movimientoSaved = movimientoRepository.save(movimiento);
        return movimientoConverter.toResponse(movimientoSaved);
    }

    private Cuenta findCuentayId(UUID cuentaId) {
        return cuentaRepository.findById(cuentaId).orElse(null);
    }

    private Movimiento findMovimientoById(UUID idMovimiento) {
        return movimientoRepository.findById(idMovimiento).orElse(null);
    }
}
