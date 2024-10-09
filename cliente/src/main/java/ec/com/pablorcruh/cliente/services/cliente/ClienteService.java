package ec.com.pablorcruh.cliente.services.cliente;

import ec.com.pablorcruh.cliente.dtos.request.ClienteDTORequest;
import ec.com.pablorcruh.cliente.dtos.response.ClienteDTOResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ClienteService {
    ClienteDTOResponse createCliente(ClienteDTORequest request);
    ClienteDTOResponse updateCliente(UUID id,ClienteDTORequest request);
    void softDeleteCliente(UUID id);
    Page<ClienteDTOResponse> getAllClientesActive(int page, int size);
}
