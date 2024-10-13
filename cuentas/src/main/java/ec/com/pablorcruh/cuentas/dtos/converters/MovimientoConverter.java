package ec.com.pablorcruh.cuentas.dtos.converters;

import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoByDateDTOResponse;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class MovimientoConverter {

    private final ModelMapper modelMapper;

    public MovimientoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Movimiento toEntity(MovimientoDTORequest request){
        return modelMapper.map(request, Movimiento.class);
    }

    public MovimientoDTOResponse toResponse(Movimiento entity){
        MovimientoDTOResponse response = MovimientoDTOResponse
                .builder()
                .id(entity.getId())
                .movementType(entity.getMovementType())
                .accountType(entity.getCuenta().getAccountType())
                .initialBalance(entity.getBalance())
                .build();
        return response;
    }

    public MovimientoByDateDTOResponse toResponseFilterByDate(Movimiento entity){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateToString = df.format(entity.getDate());
        MovimientoByDateDTOResponse response = MovimientoByDateDTOResponse
                .builder()
                .date(dateToString)
                .clienteName(entity.getCuenta().getClienteName())
                .accountNumber(entity.getCuenta().getAccountNumber())
                .accountType(entity.getCuenta().getAccountType())
                .movement(entity.getValue())
                .balance(entity.getBalance())
                .initialBalance(entity.getCuenta().getInitialBalance())
                .build();
        return response;
    }
}
