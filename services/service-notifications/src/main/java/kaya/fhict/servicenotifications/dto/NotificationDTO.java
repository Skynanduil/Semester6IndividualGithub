package kaya.fhict.servicenotifications.dto;

import kaya.fhict.servicenotifications.models.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {
    private UUID id;

    private UUID userId;

    private LocalDateTime dateTime;

    private String text;

    public NotificationDTO(Notification notification){
        this.id = notification.getId();
        this.dateTime = notification.getDateTime();
        this.text = notification.getText();
    }

}
