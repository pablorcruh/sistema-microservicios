package ec.com.pablorcruh.cuentas.services.cuenta;

import ec.com.pablorcruh.cuentas.dtos.converters.CuentaConverter;
import ec.com.pablorcruh.cuentas.dtos.request.CuentaDTORequest;
import ec.com.pablorcruh.cuentas.dtos.response.CuentaDTOResponse;
import ec.com.pablorcruh.cuentas.exceptions.NotFoundException;
import ec.com.pablorcruh.cuentas.models.Cuenta;
import ec.com.pablorcruh.cuentas.repositories.ClienteEventRepository;
import ec.com.pablorcruh.cuentas.repositories.CuentaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.*;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    private final ClienteEventRepository clienteEventRepository;

    private final CuentaConverter cuentaConverter;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteEventRepository clienteEventRepository, CuentaConverter cuentaConverter) {
        this.cuentaRepository = cuentaRepository;
        this.clienteEventRepository = clienteEventRepository;
        this.cuentaConverter = cuentaConverter;
    }

    @Override
    public CuentaDTOResponse createCuenta(CuentaDTORequest request) {
        boolean isClientePresent = clienteEventRepository.existsByPayload(request.getClienteName());
        if(!isClientePresent){
            throw new NotFoundException("Cliente no encontrado");
        }
        Cuenta cuenta = cuentaConverter.toEntity(request);
        cuenta.setStatus(TRUE);
        Cuenta cuentaSaved = cuentaRepository.save(cuenta);
        return cuentaConverter.toResponse(cuentaSaved);
    }

    @Override
    public CuentaDTOResponse updateCuenta(UUID id, CuentaDTORequest request) {
        Cuenta cuenta = findById(id);
        if(cuenta == null){
            throw new NotFoundException(String.format("Movimiento with id %s no found", id));
        }
        cuenta.setAccountType(request.getAccountType());
        Cuenta cuentaSaved = cuentaRepository.save(cuenta);
        return cuentaConverter.toResponse(cuentaSaved);
    }

    @Override
    public void softDeleteCuenta(UUID id) {
        Cuenta cuenta = findById(id);
        if(cuenta == null){
            throw new NotFoundException(String.format("Movimiento with id %s no found", id));
        }
        cuenta.setStatus(FALSE);
        cuentaRepository.save(cuenta);
    }

    @Override
    public Page<CuentaDTOResponse> getAllCuentasActive(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Cuenta> cuentas = cuentaRepository.findAllCuentaActive();
        Page<Cuenta> cuentaPage = new PageImpl<>(cuentas, pageable, cuentas.size());
        return cuentaPage.map(cuenta -> cuentaConverter.toResponse(cuenta));
    }


    private Cuenta findById(UUID id) {
        return cuentaRepository.findById(id).orElse(null);
    }


}
