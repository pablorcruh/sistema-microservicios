package ec.com.pablorcruh.cuentas.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaDTORequest {

    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private String clientName;
}
