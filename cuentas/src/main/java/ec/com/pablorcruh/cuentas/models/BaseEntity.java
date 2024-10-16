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

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }
}
