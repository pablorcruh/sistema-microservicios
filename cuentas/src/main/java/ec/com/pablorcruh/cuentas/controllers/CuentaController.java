package ec.com.pablorcruh.cuentas.controllers;

import ec.com.pablorcruh.cuentas.dtos.request.CuentaDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.CuentaDTOResponse;
import ec.com.pablorcruh.cuentas.services.cuenta.CuentaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaDTOResponse> createCuenta(
            @RequestBody CuentaDTORequest request
    ){
        CuentaDTOResponse response = cuentaService.createCuenta(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTOResponse> updateCuenta(
            @PathVariable(value = "id") UUID id,
            @RequestBody CuentaDTORequest request
    ){
        CuentaDTOResponse response = cuentaService.updateCuenta(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(
            @PathVariable("id")UUID id
    ){
        cuentaService.softDeleteCuenta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CuentaDTOResponse>> getAllCuentas(
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<CuentaDTOResponse> response = cuentaService.getAllCuentasActive(page, size);
        return ResponseEntity.ok().body(response);
    }

}
