package kaya.fhict.servicenotifications.exceptions;

import java.util.UUID;

public class NotificationNotFoundException extends Exception{
    public NotificationNotFoundException(UUID id){ super(String.format("A notification with the id %s was not found.", id)); }
}
