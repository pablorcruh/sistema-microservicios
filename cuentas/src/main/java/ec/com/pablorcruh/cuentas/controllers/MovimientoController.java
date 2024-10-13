package ec.com.pablorcruh.cuentas.controllers;

import ec.com.pablorcruh.cuentas.dtos.request.MovimientoDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoByDateDTOResponse;
import ec.com.pablorcruh.cuentas.dtos.response.MovimientoDTOResponse;
import ec.com.pablorcruh.cuentas.services.movimiento.MovimientoService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/cuenta/{cuentaId}/fecha")
    public ResponseEntity<Page<MovimientoByDateDTOResponse>> getMovimientoByDate(
            @PathVariable(value = "cuentaId") UUID cuentaId,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
            @RequestParam int page,
            @RequestParam int size
    ){

        Page<MovimientoByDateDTOResponse> response = movimientoService.filterMovimientoByDate(startDate,endDate,cuentaId,page, size);
        return ResponseEntity.ok().body(response);
    }

}
