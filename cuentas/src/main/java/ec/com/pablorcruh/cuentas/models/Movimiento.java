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
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_event_id_generator")
    @SequenceGenerator(name = "cliente_event_id_generator", sequenceName = "cliente_event_id_seq")
    private Long id;

    @Column
    private String movementType;
    @Column
    private Double value;
    @Column
    private Double balance;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Cuenta cuenta;
}
