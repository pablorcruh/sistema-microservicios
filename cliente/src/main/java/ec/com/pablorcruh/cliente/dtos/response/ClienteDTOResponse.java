package ec.com.pablorcruh.cliente.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTOResponse {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private String password;
}
