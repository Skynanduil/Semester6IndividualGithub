package walking.skeleton.serviceproducer.testutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import walking.skeleton.serviceproducer.dal.MessageDAL;
import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.exceptions.MessageNotFoundException;
import walking.skeleton.serviceproducer.models.Message;

import java.util.UUID;

@Service
public class databaseutil {
    private static MessageDAL messageDAL;

    @Autowired
    public databaseutil(MessageDAL messageDAL){
        databaseutil.messageDAL = messageDAL;
    }

    public static MessageDTO post(MessageDTO dto){
        Message toPost = new Message();
        toPost.setTitle(dto.getTitle());
        toPost.setContent(dto.getContent());
        return new MessageDTO(messageDAL.save(toPost));
    }

    public static boolean exists(UUID id){
        return messageDAL.existsById(id);
    }

    public static MessageDTO get(UUID id) throws MessageNotFoundException {
        return new MessageDTO(messageDAL.findById(id).orElseThrow(() -> new MessageNotFoundException(id)));
    }

    public static void delete(UUID id) throws MessageNotFoundException {
        Message toDelete = messageDAL.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageDAL.delete(toDelete);
    }

    public static MessageDTO put(MessageDTO dto) throws MessageNotFoundException {
        Message toEdit = messageDAL.findById(dto.getId()).orElseThrow(() -> new MessageNotFoundException(dto.getId()));
        if(!dto.getTitle().isEmpty()) toEdit.setTitle(dto.getTitle());
        if(!dto.getContent().isEmpty()) toEdit.setContent(dto.getContent());
        return new MessageDTO(messageDAL.save(toEdit));
    }
}
