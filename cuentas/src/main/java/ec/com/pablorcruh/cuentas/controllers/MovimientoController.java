package ec.com.pablorcruh.cuentas.controllers;

import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.models.Movimiento;
import ec.com.pablorcruh.cuentas.services.movimiento.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<MovimientoDTOResponse> createMovimiento(
            @PathVariable(value = "cuentaId") UUID cuentaId,
            @RequestBody MovimientoDTORequest request
    ){
        MovimientoDTOResponse response = movimientoService.createMovimiento(cuentaId,request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
