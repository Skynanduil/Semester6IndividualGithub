package kaya.fhict.servicenotifications.services.interfaces;

import kaya.fhict.servicenotifications.dto.NotificationDTO;
import kaya.fhict.servicenotifications.exceptions.NotificationNotFoundException;
import kaya.fhict.servicenotifications.models.Notification;

import java.util.UUID;

public interface INotificationService {
    Notification createNotification(NotificationDTO notification);
    Notification getNotificationById(UUID id) throws NotificationNotFoundException;
    Notification[] getNotificationsByUserId(UUID id);
}
