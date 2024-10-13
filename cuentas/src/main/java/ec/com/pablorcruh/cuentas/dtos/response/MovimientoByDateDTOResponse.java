package ec.com.pablorcruh.cuentas.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoByDateDTOResponse {

    private String date;

    private String clienteName;

    private String accountNumber;

    private String accountType;

    private boolean status;

    private Double movement;

    private Double initialBalance;

    private Double balance;

}
