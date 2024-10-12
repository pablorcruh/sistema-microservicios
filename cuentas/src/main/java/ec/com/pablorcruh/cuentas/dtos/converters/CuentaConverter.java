package ec.com.pablorcruh.cuentas.dtos.converters;

import ec.com.pablorcruh.cuentas.dtos.request.CuentaDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.CuentaDTOResponse;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.models.Cuenta;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class CuentaConverter {

    private final ModelMapper modelMapper;

    private final MovimientoConverter movimientoConverter;

    public CuentaConverter(ModelMapper modelMapper, MovimientoConverter movimientoConverter) {
        this.modelMapper = modelMapper;
        this.movimientoConverter = movimientoConverter;
    }

    public Cuenta toEntity(CuentaDTORequest request){
        return modelMapper.map(request, Cuenta.class);
    }

    public CuentaDTOResponse toResponse(Cuenta entity){
        Set<Movimiento> movimientosEntity = entity.getMovimientos();
        CuentaDTOResponse response = CuentaDTOResponse
                .builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType())
                .clienteName(entity.getClienteName())
                .initialBalance(entity.getInitialBalance())
                .movimientos(movimientosEntity.stream().map(m -> movimientoConverter.toResponse(m)).collect(Collectors.toList()))
                .build();
        return response;
    }
}
