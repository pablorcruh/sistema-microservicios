package ec.com.pablorcruh.cliente.services.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.pablorcruh.cliente.dtos.converter.ClienteConverter;
import ec.com.pablorcruh.cliente.dtos.request.ClienteDTORequest;
import ec.com.pablorcruh.cliente.dtos.response.ClienteDTOResponse;
import ec.com.pablorcruh.cliente.exceptions.NotFoundException;
import ec.com.pablorcruh.cliente.models.Cliente;
import ec.com.pablorcruh.cliente.models.ClienteCreatedEvent;
import ec.com.pablorcruh.cliente.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.*;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    private final ClienteConverter clienteConverter;

    private final ClienteEventService clienteEventService;

    private final ObjectMapper objectMapper;


    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteConverter clienteConverter, ClienteEventService clienteEventService, ObjectMapper objectMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteConverter = clienteConverter;
        this.clienteEventService = clienteEventService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ClienteDTOResponse createCliente(ClienteDTORequest request) {
        Cliente cliente = clienteConverter.toEntity(request);
        cliente.setStatus(TRUE);
        Cliente clienteSaved = clienteRepository.save(cliente);
        ClienteCreatedEvent clienteCreatedEvent = new ClienteCreatedEvent(
                UUID.randomUUID().toString(),
                cliente.getName(),
                LocalDateTime.now());
        clienteEventService.save(clienteCreatedEvent);
        return clienteConverter.toResponse(clienteSaved);
    }

    @Override
    public ClienteDTOResponse updateCliente(UUID id,ClienteDTORequest request) {
        Cliente cliente = findClienteById(id);
        if(cliente == null){
            throw new NotFoundException(String.format("client with id %s not found", id));
        }
        cliente.setPassword(request.getPassword());
        cliente.setName(request.getName());
        cliente.setAddress(request.getAddress());
        cliente.setPassword(request.getPassword());
        Cliente savedClient = clienteRepository.save(cliente);
        return clienteConverter.toResponse(savedClient);
    }

    @Override
    public void softDeleteCliente(UUID id) {
        Cliente cliente = findClienteById(id);
        if(cliente == null){
            throw new NotFoundException(String.format("client with id %s not found", id));
        }
        cliente.setStatus(FALSE);
        clienteRepository.save(cliente);
    }

    @Override
    public Page<ClienteDTOResponse> getAllClientesActive(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Cliente> clientes = clienteRepository.findAllActive();
        Page<Cliente> clientePage = new PageImpl<>(clientes, pageable, clientes.size());
        return clientePage.map(c -> clienteConverter.toResponse(c));
    }

    private Cliente findClienteById(UUID id) {
        return clienteRepository.findById(id).orElse(null);
    }

    private String toJsonPayload(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
