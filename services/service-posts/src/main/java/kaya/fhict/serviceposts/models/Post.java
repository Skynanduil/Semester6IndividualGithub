package kaya.fhict.serviceposts.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = Post.TABLE_NAME)
public class Post {
    final static String TABLE_NAME = "post";

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", columnDefinition = "uuid")
    private UUID id;

    @CreationTimestamp
    @Column(name = "date_published")
    private LocalDateTime datePublished;

    //@ManyToOne
    @Type(type = "pg-uuid")
    @Column(name = "publisher")
    private final UUID publisher;

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String image;

    @Column(name = "markdown")
    private String markdown;
}
