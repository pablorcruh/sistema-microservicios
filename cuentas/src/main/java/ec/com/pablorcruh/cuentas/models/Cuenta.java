package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta extends BaseEntity{

    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cuenta")
    private Set<Movimiento> movimientos = new HashSet<>();
}
