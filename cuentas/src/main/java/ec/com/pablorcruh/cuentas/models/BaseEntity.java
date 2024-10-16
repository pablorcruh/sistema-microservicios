package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    private UUID id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @PrePersist
    public void prePersist() {
        this.date = new Date(System.currentTimeMillis());
        this.id = UUID.randomUUID();
    }
}
