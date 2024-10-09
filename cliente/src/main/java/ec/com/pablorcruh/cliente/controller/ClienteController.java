package ec.com.pablorcruh.cliente.controller;

import ec.com.pablorcruh.cliente.dtos.request.ClienteDTORequest;
import ec.com.pablorcruh.cliente.dtos.response.ClienteDTOResponse;
import ec.com.pablorcruh.cliente.services.cliente.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTOResponse> createCliente(
            @RequestBody ClienteDTORequest request
    ){
        ClienteDTOResponse response = clienteService.createCliente(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTOResponse> updateCliente(
            @PathVariable(value = "id")UUID id,
            @RequestBody ClienteDTORequest request
            ){
           ClienteDTOResponse response = clienteService.updateCliente(id, request);
           return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(
            @PathVariable("id")UUID id
    ){
        clienteService.softDeleteCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTOResponse>> getAllClientes(
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<ClienteDTOResponse> response = clienteService.getAllClientesActive(page, size);
        return ResponseEntity.ok().body(response);
    }
}
