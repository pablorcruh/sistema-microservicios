package ec.com.pablorcruh.cuentas.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoDTORequest {

    private String accountType;
    private Double initialBalance;
    private String movementType;
}
