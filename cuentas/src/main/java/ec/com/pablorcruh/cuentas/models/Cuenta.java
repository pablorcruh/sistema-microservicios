package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.*;
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

    @Column
    private String accountNumber;

    @Column
    private String accountType;

    @Column
    private Double initialBalance;

    @Column
    private Boolean status;

    @Column
    private String clienteName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cuenta")
    private Set<Movimiento> movimientos = new HashSet<>();
}
