package ec.com.pablorcruh.cuentas.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_events")
@Getter
@Setter
@NoArgsConstructor
public class ClienteEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_event_id_generator")
    @SequenceGenerator(name = "cliente_event_id_generator", sequenceName = "cliente_event_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String eventId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(columnDefinition="text")
    private String payload;

    public ClienteEvent(String eventId, String payload, LocalDateTime createdAt) {
        this.eventId = eventId;
        this.payload = payload;
        this.createdAt = createdAt;
    }
}
