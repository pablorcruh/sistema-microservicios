package ec.com.pablorcruh.cuentas.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaDTOResponse {
    private UUID id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private List<MovimientoDTOResponse> movimientos;
}
