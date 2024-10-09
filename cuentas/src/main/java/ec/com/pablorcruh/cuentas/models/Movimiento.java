package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    private String movementType;
    private Double value;
    private Double balance;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Cuenta cuenta;
}
