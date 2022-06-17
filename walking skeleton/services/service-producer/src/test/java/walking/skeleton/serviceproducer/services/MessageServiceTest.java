package walking.skeleton.serviceproducer.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import walking.skeleton.serviceproducer.dal.MessageDAL;
import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.exceptions.MessageNotFoundException;
import walking.skeleton.serviceproducer.models.Message;

import java.util.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageServiceTest {
    @Test
    public void CreateMessage_Should_CreateMessageInDatastore(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);
        MessageDTO toSave = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        toSave.setTitle(testTitle);
        toSave.setContent(testContent);

        // Act
        Message result = messageService.createMessage(toSave);

        // Assert
        assertThat(result.getTitle())
                .isNotNull()
                .isEqualTo(testTitle);
        assertThat(result.getContent())
                .isNotNull()
                .isEqualTo(testContent);
    }

    @Test
    public void GetMessageById_Should_RetrieveMessageFromDatastore_ById(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);
        MessageDTO toSave = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        toSave.setTitle(testTitle);
        toSave.setContent(testContent);
        UUID id = messageService.createMessage(toSave).getId();

        // Act
        try {
            Message result = messageService.getMessageById(id);

            // Assert
            assertThat(result.getId())
                    .isNotNull()
                    .isEqualTo(id);
            assertThat(result.getTitle())
                    .isNotNull()
                    .isEqualTo(testTitle);
            assertThat(result.getContent())
                    .isNotNull()
                    .isEqualTo(testContent);

        } catch (MessageNotFoundException e) {
            fail("Test MessageService.getMessageById(" + id + ") threw exception: " + e.getMessage());
        }
    }

    @Test
    public void GetMessageById_ShouldThrow_MessageNotFoundException_WhenMessageNotExist(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);
        UUID id = UUID.randomUUID();

        // Act
        try {
            messageService.getMessageById(id);

            // Assert
            fail("Test should've thrown error, it did not.");

        } catch (MessageNotFoundException e) {
            assertThat(e)
                    .isInstanceOf(MessageNotFoundException.class);
        }
    }

    @Test
    public void EditMessage_Should_EditMessageInDatastore(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);

        MessageDTO toSave = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        toSave.setTitle(testTitle);
        toSave.setContent(testContent);

        UUID id = messageService.createMessage(toSave).getId();

        String newTitle = "New Title";
        String newContent = "New Content";
        MessageDTO editedMessage = new MessageDTO();
        editedMessage.setId(id);
        editedMessage.setTitle(newTitle);
        editedMessage.setContent(newContent);

        // Act
        try {
            Message result = messageService.editMessage(editedMessage);

            // Assert
            assertThat(result.getId())
                    .isNotNull()
                    .isEqualTo(id);
            assertThat(result.getTitle())
                    .isNotNull()
                    .isEqualTo(newTitle);
            assertThat(result.getContent())
                    .isNotNull()
                    .isEqualTo(newContent);
        } catch (MessageNotFoundException e) {
            fail("Test MessageService.editMessage(with id: " + id + ", Title: " + newTitle + " and content: " + newContent + ") threw exception: " + e.getMessage());
        }
    }

    @Test
    public void EditMessage_ShouldThrow_MessageNotFoundException_WhenMessageNotExist(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);
        UUID id = UUID.randomUUID();
        MessageDTO notExistingMessage = new MessageDTO();
        notExistingMessage.setId(id);

        // Act
        try {
            messageService.editMessage(notExistingMessage);

            // Assert
            fail("Test should've thrown error, it did not.");
        } catch (MessageNotFoundException e) {
            assertThat(e)
                    .isInstanceOf(MessageNotFoundException.class);
        }
    }

    @Test
    public void DeleteMessageById_Should_DeleteMessageFromDatastore_ById(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);
        MessageDTO toSave = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        toSave.setTitle(testTitle);
        toSave.setContent(testContent);
        UUID id = messageService.createMessage(toSave).getId();

        // Act
        try {
            messageService.deleteMessageById(id);

            // Assert
            messageService.getMessageById(id);

            fail("Test should've thrown error, it did not.");
        } catch (MessageNotFoundException e) {
            assertThat(e)
                    .isInstanceOf(MessageNotFoundException.class);
        }
    }

    @Test
    public void DeleteMessageById_ShouldThrow_MessageNotFoundException_WhenMessageNotExist(){
        // Arrange
        MessageDAL messageDAL = new TestDAL();
        IMessageService messageService = new MessageService(messageDAL);
        UUID id = UUID.randomUUID();

        // Act
        try {
            messageService.deleteMessageById(id);

            // Assert
            fail("Test should've thrown error, it did not.");
        } catch (MessageNotFoundException e) {
            assertThat(e)
                    .isInstanceOf(MessageNotFoundException.class);
        }
    }

    private static class TestDAL implements MessageDAL {
        private static HashMap<UUID, Message> storedMessages;

        public TestDAL(){
            storedMessages = new HashMap<>();
        }

        @Override
        public <S extends Message> S save(S entity) {
            if(entity.getId() == null){
                entity.setId(UUID.randomUUID());
            }
            UUID id = entity.getId();
            storedMessages.put(id, entity);
            return (S)storedMessages.get(id);
        }

        @Override
        public <S extends Message> Iterable<S> saveAll(Iterable<S> entities) {
            return null; // NOT BEING USED
        }

        @Override
        public Optional<Message> findById(UUID uuid) {
            return Optional.ofNullable(storedMessages.get(uuid));
        }

        @Override
        public boolean existsById(UUID uuid) {
            return storedMessages.containsKey(uuid);
        }

        @Override
        public Iterable<Message> findAll() {
            return storedMessages.values();
        }

        @Override
        public Iterable<Message> findAllById(Iterable<UUID> uuids) {
            return null; // NOT BEING USED
        }

        @Override
        public long count() {
            return 0; // NOT BEING USED
        }

        @Override
        public void deleteById(UUID uuid) {
            // NOT BEING USED
        }

        @Override
        public void delete(Message entity) {
            storedMessages.remove(entity.getId());
        }

        @Override
        public void deleteAllById(Iterable<? extends UUID> uuids) {
            // NOT BEING USED
        }

        @Override
        public void deleteAll(Iterable<? extends Message> entities) {
            // NOT BEING USED
        }

        @Override
        public void deleteAll() {
            // NOT BEING USED
        }
    }
}
