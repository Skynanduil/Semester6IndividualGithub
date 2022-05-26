package walking.skeleton.serviceproducer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends Exception{
    public MessageNotFoundException(UUID id){
        super(String.format("A Message with id %s was not found.", id));
    }
}
