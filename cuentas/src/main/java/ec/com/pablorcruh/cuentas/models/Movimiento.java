package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento extends BaseEntity{

    @Column
    private String movementType;
    @Column
    private Double value;
    @Column
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Cuenta cuenta;
}
