package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
