package ec.com.pablorcruh.cliente.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTORequest {
    private String name;
    private String address;
    private String phone;
    private String password;
}
