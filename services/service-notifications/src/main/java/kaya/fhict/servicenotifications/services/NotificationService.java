package kaya.fhict.servicenotifications.services;

import kaya.fhict.servicenotifications.dal.interfaces.INotificationDAL;
import kaya.fhict.servicenotifications.dto.NotificationDTO;
import kaya.fhict.servicenotifications.exceptions.NotificationNotFoundException;
import kaya.fhict.servicenotifications.models.Notification;
import kaya.fhict.servicenotifications.services.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Qualifier("notificationService")
public class NotificationService implements INotificationService {
    private final INotificationDAL notificationDAL;

    @Autowired
    public NotificationService(INotificationDAL notificationDAL){
        this.notificationDAL = notificationDAL;
    }

    @Override
    public Notification createNotification(NotificationDTO notification) {
        Notification notificationInDB = new Notification();

        notificationInDB.setText(notification.getText());
        notificationInDB.setUserId(notification.getUserId());

        return notificationDAL.save(notificationInDB);
    }

    @Override
    public Notification getNotificationById(UUID id) throws NotificationNotFoundException {
        return notificationDAL.findById(id).orElseThrow(() -> new NotificationNotFoundException(id));
    }

    @Override
    public Notification[] getNotificationsByUserId(UUID userId) {
        return notificationDAL.findAllByUserId(userId);
    }
}
