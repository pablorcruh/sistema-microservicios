package ec.com.pablorcruh.cliente.util;

import ec.com.pablorcruh.cliente.dtos.request.ClienteDTORequest;
import ec.com.pablorcruh.cliente.models.Cliente;

import java.util.UUID;

import static java.lang.Boolean.*;

public class ClienteCreationUtils {

    public static Cliente createCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(UUID.fromString("df2729eb-bd1f-401a-8259-2094f4a9d4ac"));
        cliente.setAddress("Calle falsa 123");
        cliente.setPassword("1234");
        cliente.setPhone("0987654321");
        cliente.setName("Pablo Cruz");
        cliente.setStatus(TRUE);
        cliente.setAge(34);
        cliente.setGender("MALE");
        return cliente;
    }

    public static ClienteDTORequest createClienteDTORequest() {
        ClienteDTORequest request = ClienteDTORequest
                .builder()
                .phone("0987654321")
                .name("Pablo Cruz")
                .address("Calle falsa 123")
                .password("1234")
                .build();
        return request;
    }
}
