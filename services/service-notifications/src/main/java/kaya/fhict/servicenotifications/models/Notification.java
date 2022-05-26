package kaya.fhict.servicenotifications.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = Notification.TABLE_NAME)
public class Notification {
    final static String TABLE_NAME = "notification";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private UUID id;

    //@ManyToOne
    @Column(name = "user_id")
    private UUID userId;

    @CreationTimestamp
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "text")
    private String text;
}
