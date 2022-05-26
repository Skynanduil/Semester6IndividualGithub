package walking.skeleton.serviceproducer.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = Message.TABLE_NAME)
public class Message {
    final static String TABLE_NAME = "message";

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id", columnDefinition = "uuid")
    private UUID id;

    @CreationTimestamp
    @Column(name = "date_published")
    private LocalDateTime datePublished;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
