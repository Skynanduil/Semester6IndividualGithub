package walking.skeleton.serviceproducer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import walking.skeleton.serviceproducer.controllers.DefaultController;
import walking.skeleton.serviceproducer.dal.MessageDAL;
import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.exceptions.MessageNotFoundException;
import walking.skeleton.serviceproducer.models.Message;

import java.util.UUID;

@Service
@Qualifier("message_service")
public class MessageService implements IMessageService{

    private final MessageDAL messageDAL;
    private final static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    public MessageService(MessageDAL messageDAL){
        this.messageDAL = messageDAL;
    }

    @Override
    public Message createMessage(MessageDTO message) {
        Message toDB = new Message();
        toDB.setTitle(message.getTitle());
        toDB.setContent(message.getContent());
        Message saved = messageDAL.save(toDB);
        logger.info(String.format("Message created with ID [%1$s], title [%2$s] and content [%3$s].\nThe message was published at[%4$s]", saved.getId(), saved.getTitle(), saved.getContent(), saved.getDatePublished()));
        return saved;
    }

    @Override
    public Message getMessageById(UUID id) throws MessageNotFoundException {
        Message message = messageDAL.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        logger.info(String.format("Message found with ID [%1$s], title [%2$s] and content [%3$s]", id, message.getTitle(), message.getContent()));
        return message;
    }

    @Override
    public Message editMessage(MessageDTO message) throws MessageNotFoundException {
        Message toEdit = getMessageById(message.getId());
        toEdit.setTitle(message.getTitle());
        toEdit.setContent(message.getContent());
        Message saved = messageDAL.save(toEdit);
        logger.info(String.format("Message saved with ID [%1$s], title [%2$s] and content [%3$s]", message.getId(), message.getTitle(), message.getContent()));
        return saved;
    }

    @Override
    public void deleteMessageById(UUID id) throws MessageNotFoundException {
        Message toDelete = getMessageById(id);
        messageDAL.delete(toDelete);
        logger.info(String.format("Message deleted with ID [%1$s], title [%2$s] and content [%3$s]", id, toDelete.getTitle(), toDelete.getContent()));
    }
}
