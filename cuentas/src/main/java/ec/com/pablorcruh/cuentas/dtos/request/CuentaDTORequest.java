package ec.com.pablorcruh.cuentas.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaDTORequest {

    @NotNull
    private String accountNumber;
    @NotNull
    private String accountType;
    @NotNull
    private String clienteName;
}
