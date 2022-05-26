package walking.skeleton.serviceproducer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import walking.skeleton.serviceproducer.models.Message;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private UUID id;

    private LocalDateTime datePublished;

    private String title;

    private String content;

    public MessageDTO(Message message){
        this.id = message.getId();
        this.datePublished = message.getDatePublished();
        this.title = message.getTitle();
        this.content = message.getContent();
    }
}
