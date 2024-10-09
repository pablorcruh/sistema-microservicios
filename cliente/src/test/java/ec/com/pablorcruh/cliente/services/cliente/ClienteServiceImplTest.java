package ec.com.pablorcruh.cliente.services.cliente;

import ec.com.pablorcruh.cliente.dtos.converter.ClienteConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import ec.com.pablorcruh.cliente.dtos.response.ClienteDTOResponse;
import ec.com.pablorcruh.cliente.models.Cliente;
import ec.com.pablorcruh.cliente.repositories.ClienteRepository;
import ec.com.pablorcruh.cliente.util.ClienteCreationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    ClienteServiceImpl underTest;

    @Mock
    ClienteRepository repository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ClienteConverter converter;

    @BeforeEach
    void setup(){
        modelMapper = new ModelMapper();
        converter = new ClienteConverter(modelMapper);
        underTest = new ClienteServiceImpl(repository, converter);
    }


    @Test
    void shouldCreateCliente(){
        when(repository.save(any(Cliente.class))).thenReturn(ClienteCreationUtils.createCliente());
        ClienteDTOResponse response = underTest.createCliente(ClienteCreationUtils.createClienteDTORequest());
        assertNotNull(response);
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void shouldUpdateCliente(){
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(ClienteCreationUtils.createCliente()));
        when(repository.save(any(Cliente.class))).thenReturn(ClienteCreationUtils.createCliente());
        ClienteDTOResponse response = underTest.updateCliente(UUID.randomUUID(), ClienteCreationUtils.createClienteDTORequest());
        verify(repository,times(1)).save(any(Cliente.class));
        verify(repository,times(1)).findById(any(UUID.class));
        assertThat(response.getName().equals("Pablo Cruz"));
    }
}