package walking.skeleton.serviceproducer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.exceptions.MessageNotFoundException;
import walking.skeleton.serviceproducer.services.IMessageService;

import java.util.UUID;

@RestController
@RequestMapping("/producer/message")
public class MessageController {

    private final static Logger logger = LoggerFactory.getLogger(DefaultController.class);
    private final IMessageService messageService;

    @Autowired
    public MessageController(@Qualifier("message_service") IMessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO message){
        logger.info(String.format("Attempting to create message with Title [%1$s] and content [%2$s]", message.getTitle(), message.getContent()));
        MessageDTO createdMessage = new MessageDTO(messageService.createMessage(message));
        return new ResponseEntity<MessageDTO>(createdMessage, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable UUID id) throws MessageNotFoundException {
        logger.info(String.format("Attempting to retrieve message with ID [%s]", id));
        MessageDTO foundMessage = new MessageDTO(messageService.getMessageById(id));
        return new ResponseEntity<MessageDTO>(foundMessage, HttpStatus.OK);
    }

    @PutMapping(value = "/edit", consumes = "application/json")
    public ResponseEntity<MessageDTO> editMessage(@RequestBody MessageDTO message) throws MessageNotFoundException {
        logger.info(String.format("Attempting to edit message with ID [%1$s].\nThe new title is [%2$s] and the new content is [%3$s].", message.getId(), message.getTitle(), message.getContent()));
        MessageDTO editedMessage = new MessageDTO(messageService.editMessage(message));
        return new ResponseEntity<MessageDTO>(editedMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID id) throws MessageNotFoundException {
        logger.info(String.format("Attempting to delete message with ID [%s]", id));
        messageService.deleteMessageById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
