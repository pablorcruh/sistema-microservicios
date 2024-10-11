package ec.com.pablorcruh.cliente.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    private String gender;

    private int age;

    private String address;

    private String phone;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }
}
