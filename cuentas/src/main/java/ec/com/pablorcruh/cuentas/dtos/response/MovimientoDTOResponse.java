package ec.com.pablorcruh.cuentas.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoDTOResponse {

    private UUID id;
    private String accountType;
    private Double initialBalance;
    private String movementType;
}
