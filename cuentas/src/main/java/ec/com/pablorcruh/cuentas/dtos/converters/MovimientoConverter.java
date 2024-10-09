package ec.com.pablorcruh.cuentas.dtos.converters;

import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

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
}
