package kaya.fhict.servicenotifications.dal.interfaces;

import kaya.fhict.servicenotifications.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface INotificationDAL extends CrudRepository<Notification, UUID> {
    Notification[] findAllByUserId(UUID userId);
}
