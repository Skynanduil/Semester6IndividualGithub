package walking.skeleton.serviceproducer.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.exceptions.MessageNotFoundException;
import walking.skeleton.serviceproducer.models.Message;
import walking.skeleton.serviceproducer.services.IMessageService;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageControllerTest {
    @Test
    public void CreateMessage_Should_SendMessage_WithCorrectTitleAndContent(){
        // Arrange
        IMessageService testService = new TestService();
        MessageController messageController = new MessageController(testService);
        MessageDTO messageSent = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        messageSent.setTitle(testTitle);
        messageSent.setContent(testContent);

        // Act
        ResponseEntity<MessageDTO> result = messageController.createMessage(messageSent);

        // Assert
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        assertThat(result.getBody().getTitle())
                .isNotNull()
                .isEqualTo(testTitle);

        assertThat(result.getBody().getContent())
                .isNotNull()
                .isEqualTo(testContent);
    }

    @Test
    public void GetMessage_Should_RetrieveMessage_WithCorrectId(){
        // Arrange
        IMessageService testService = new TestService();
        MessageController messageController = new MessageController(testService);
        UUID id = UUID.randomUUID();

        // Act
        try {
            ResponseEntity<MessageDTO> result = messageController.getMessage(id);

            // Assert
            assertThat(result.getStatusCode())
                    .isEqualTo(HttpStatus.OK);

            assertThat(result.getBody().getId())
                    .isNotNull()
                    .isEqualTo(id);
        } catch (MessageNotFoundException e) {
            fail("MessageController.getMessage(" + id + ") threw exception: " + e.getMessage());
        }
    }

    @Test
    public void EditMessage_Should_EditMessage_WithCorrectTitleAndContent(){
        // Arrange
        IMessageService testService = new TestService();
        MessageController messageController = new MessageController(testService);
        MessageDTO messageSent = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        messageSent.setTitle(testTitle);
        messageSent.setContent(testContent);

        // Act
        ResponseEntity<MessageDTO> result = null;
        try {
            result = messageController.editMessage(messageSent);

            // Assert
            assertThat(result.getStatusCode())
                    .isEqualTo(HttpStatus.OK);

            assertThat(result.getBody().getTitle())
                    .isNotNull()
                    .isEqualTo(testTitle);

            assertThat(result.getBody().getContent())
                    .isNotNull()
                    .isEqualTo(testContent);
        } catch (MessageNotFoundException e) {
            fail("MessageController.editMessage(with title: " + testTitle + ", and content: " +testContent + ") threw exception: " + e.getMessage());
        }
    }

    @Test
    public void DeleteMessage_Should_OK(){
        // Arrange
        IMessageService testService = new TestService();
        MessageController messageController = new MessageController(testService);
        UUID id = UUID.randomUUID();

        // Act
        try {
            ResponseEntity<Void> result = messageController.deleteMessage(id);

            // Assert
            assertThat(result.getStatusCode())
                    .isEqualTo(HttpStatus.OK);
        } catch (MessageNotFoundException e) {
            fail("MessageController.deleteMessage(" + id + ") threw exception: " + e.getMessage());
        }
    }

    private static class TestService implements IMessageService{
        @Override
        public Message createMessage(MessageDTO message) {
            Message toReturn = new Message();
            toReturn.setTitle(message.getTitle());
            toReturn.setContent(message.getContent());
            toReturn.setId(message.getId());
            toReturn.setDatePublished(message.getDatePublished());
            return toReturn;
        }

        @Override
        public Message getMessageById(UUID id) throws MessageNotFoundException {
            Message toReturn = new Message();
            toReturn.setId(id);
            return toReturn;
        }

        @Override
        public Message editMessage(MessageDTO message) throws MessageNotFoundException {
            Message toReturn = new Message();
            toReturn.setTitle(message.getTitle());
            toReturn.setContent(message.getContent());
            toReturn.setId(message.getId());
            toReturn.setDatePublished(message.getDatePublished());
            return toReturn;
        }

        @Override
        public void deleteMessageById(UUID id) throws MessageNotFoundException {
            // Do nothing.
        }
    }
}
