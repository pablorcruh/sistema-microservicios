package ec.com.pablorcruh.cuentas.services.movimiento;

import ec.com.pablorcruh.cuentas.dtos.converters.MovimientoConverter;
import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.exceptions.InsufficientBalanceException;
import ec.com.pablorcruh.cuentas.exceptions.NotFoundException;
import ec.com.pablorcruh.cuentas.models.Cuenta;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import ec.com.pablorcruh.cuentas.repositories.CuentaRepository;
import ec.com.pablorcruh.cuentas.repositories.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Double processBalance;
        Movimiento  movimientoAux;
        Cuenta cuenta = findCuentayId(cuentaId);
        if(cuenta == null){
            throw new NotFoundException(String.format("Cuenta with id %s no found", cuentaId));
        }
        if(Double.compare(request.getValue(),cuenta.getInitialBalance()) > 0){
            throw new InsufficientBalanceException("Saldo Insuficiente para realizar movimiento");
        }
        List<Movimiento> movimientos = movimientoRepository.getMovimientosByAccount(cuenta.getId());
        if(movimientos.size() > 0){
            movimientoAux = movimientos.get(0);
            processBalance = movimientoAux.getBalance() + request.getValue();
        }else{
            processBalance = cuenta.getInitialBalance() + request.getValue();
        }
        Movimiento movimiento = movimientoConverter.toEntity(request);
        movimiento.setDate(new Date());
        movimiento.setCuenta(cuenta);
        movimiento.setBalance(processBalance);
        movimiento.setValue(request.getValue());
        if(request.getValue()>0){
            movimiento.setMovementType("Deposito: "+ request.getValue());
        }else{
            movimiento.setMovementType("Retiro: "+ request.getValue());
        }
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
