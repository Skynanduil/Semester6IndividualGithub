package walking.skeleton.serviceproducer.services;

import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.exceptions.MessageNotFoundException;
import walking.skeleton.serviceproducer.models.Message;

import java.util.UUID;

public interface IMessageService {
    Message createMessage(MessageDTO message);
    Message getMessageById(UUID id) throws MessageNotFoundException;
    Message editMessage(MessageDTO message) throws MessageNotFoundException;
    void deleteMessageById(UUID id) throws MessageNotFoundException;
}
