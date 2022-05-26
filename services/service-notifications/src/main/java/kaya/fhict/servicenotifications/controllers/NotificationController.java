package kaya.fhict.servicenotifications.controllers;

import kaya.fhict.servicenotifications.dto.NotificationDTO;
import kaya.fhict.servicenotifications.exceptions.NotificationNotFoundException;
import kaya.fhict.servicenotifications.services.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {
    private final INotificationService notificationService;

    @Autowired
    public NotificationController(@Qualifier("notificationService") INotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable UUID id) throws NotificationNotFoundException {
        NotificationDTO notification = new NotificationDTO(notificationService.getNotificationById(id));
        return new ResponseEntity<NotificationDTO>(notification, HttpStatus.OK);
    }

    //@GetMapping("/all/{id}")
    //public ResponseEntity<NotificationDTO[]> getNotificationsByUserId(@PathVariable UUID userId){
    //    // TODO: NotificationDTO[] notifications = Arrays.stream(notificationService.getNotificationsByUserId(userId)).map(o -> new NotificationDTO(o)).toArray();
    //}
}
