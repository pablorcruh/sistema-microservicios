package ec.com.pablorcruh.cuentas.services.movimiento;

import ec.com.pablorcruh.cuentas.dtos.converters.MovimientoConverter;
import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoByDateDTOResponse;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.exceptions.InsufficientBalanceException;
import ec.com.pablorcruh.cuentas.exceptions.NotFoundException;
import ec.com.pablorcruh.cuentas.models.Cuenta;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import ec.com.pablorcruh.cuentas.repositories.CuentaRepository;
import ec.com.pablorcruh.cuentas.repositories.MovimientoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        List<Movimiento> movimientos = movimientoRepository.getMovimientosByAccount(cuenta.getId());
        if(movimientos.size() > 0){
            movimientoAux = movimientos.get(0);
            if(request.getValue()<0){
                // retiro
                if(Math.signum(request.getValue())<0){
                    if(Math.abs(request.getValue()) > movimientoAux.getBalance()){
                        throw new InsufficientBalanceException("Saldo Insuficiente para realizar movimiento");
                    }
                }
            }
            processBalance = movimientoAux.getBalance() + request.getValue();
        }else{
            if(request.getValue()<0){
                // retiro
                if(Math.signum(request.getValue())<0){
                    if(Math.abs(request.getValue()) > cuenta.getInitialBalance()){
                        throw new InsufficientBalanceException("Saldo Insuficiente para realizar movimiento");
                    }
                }
            }
            processBalance = cuenta.getInitialBalance() + request.getValue();
        }
        Movimiento movimiento = movimientoConverter.toEntity(request);
        movimiento.setDate(new Date(System.currentTimeMillis()));
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

    @Override
    public Page<MovimientoByDateDTOResponse> filterMovimientoByDate(
            Date startDate,
            Date endDate,
            UUID accountId,
            int page,
            int size) {
        Cuenta cuenta = findCuentayId(accountId);
        if(cuenta == null){
            throw new NotFoundException(String.format("Cuenta with id %s no found", accountId));
        }
        Pageable pageable = PageRequest.of(page, size);
        List<Movimiento> movimientos = movimientoRepository.getMovimientosByDate(startDate, endDate,accountId);
        Page<Movimiento> movimientosPage = new PageImpl<>(movimientos, pageable, movimientos.size());
        return movimientosPage.map(m -> movimientoConverter.toResponseFilterByDate(m));
    }

    private Cuenta findCuentayId(UUID cuentaId) {
        return cuentaRepository.findById(cuentaId).orElse(null);
    }

    private Movimiento findMovimientoById(UUID idMovimiento) {
        return movimientoRepository.findById(idMovimiento).orElse(null);
    }
}
