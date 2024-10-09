package ec.com.pablorcruh.cliente.dtos.converter;

import ec.com.pablorcruh.cliente.dtos.request.ClienteDTORequest;
import ec.com.pablorcruh.cliente.dtos.response.ClienteDTOResponse;
import ec.com.pablorcruh.cliente.models.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConverter {

    private final ModelMapper modelMapper;

    public ClienteConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cliente toEntity(ClienteDTORequest request){
        return modelMapper.map(request, Cliente.class);
    }

    public ClienteDTOResponse toResponse(Cliente cliente){
        ClienteDTOResponse response = ClienteDTOResponse
                .builder()
                .id(cliente.getId())
                .address(cliente.getAddress())
                .name(cliente.getName())
                .phone(cliente.getPhone())
                .password(cliente.getPassword())
                .build();
        return response;
    }

}
