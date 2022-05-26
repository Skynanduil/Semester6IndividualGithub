package kaya.fhict.serviceposts.dto;

import kaya.fhict.serviceposts.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private UUID id;

    private LocalDateTime datePublished;

    private UUID publisher;

    private String text;

    private String image;

    private String markdown;

    public PostDTO(Post post){
        this.id = post.getId();
        this.datePublished = post.getDatePublished();
        this.publisher = post.getPublisher();
        this.text = post.getText();
        this.image = post.getImage();
        this.markdown = post.getMarkdown();
    }
}
