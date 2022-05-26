package kaya.fhict.serviceposts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends Exception{
    public PostNotFoundException(UUID id){ super(String.format("A post with the id %s was not found.", id));}
}
