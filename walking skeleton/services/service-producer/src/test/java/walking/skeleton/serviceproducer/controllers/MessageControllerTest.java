package walking.skeleton.serviceproducer.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import walking.skeleton.serviceproducer.dto.MessageDTO;
import walking.skeleton.serviceproducer.testutil.databaseutil;
import walking.skeleton.serviceproducer.testutil.util;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    public void CreateMessage_Should_SendMessage_WithCorrectTitleAndContent() throws Exception {
        // Arrange
        MessageDTO messageSent = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        messageSent.setTitle(testTitle);
        messageSent.setContent(testContent);

        // Act
        mockMvc.perform(post("/producer/message/create")
                                .content(util.asJsonString(messageSent))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))


                // Assert
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(testTitle))
                .andExpect(jsonPath("$.content").value(testContent));
    }

    @Test
    public void GetMessage_Should_RetrieveMessage_WithCorrectId() throws Exception {
        // Arrange
        MessageDTO message = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        message.setTitle(testTitle);
        message.setContent(testContent);
        MessageDTO messageInDatabase = databaseutil.post(message);
        UUID id = messageInDatabase.getId();

        // Act
        mockMvc.perform(get("/producer/message/" + id))

                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.title").value(testTitle))
                .andExpect(jsonPath("$.content").value(testContent));
    }

    @Test
    public void EditMessage_Should_EditMessage_WithCorrectTitleAndContent() throws Exception {
        // Arrange
        MessageDTO message = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        message.setTitle(testTitle);
        message.setContent(testContent);
        MessageDTO messageInDatabase = databaseutil.post(message);
        UUID id = messageInDatabase.getId();
        String newTitle = "New Title";
        String newContent = "New Content";
        message.setId(id);
        message.setTitle(newTitle);
        message.setContent(newContent);

        // Act
        mockMvc.perform(put("/producer/message/edit")
                .content(util.asJsonString(message))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.title").value(newTitle))
                .andExpect(jsonPath("$.content").value(newContent));
    }

    @Test
    public void DeleteMessage_Should_OK() throws Exception {
        // Arrange
        MessageDTO message = new MessageDTO();
        String testTitle = "Test Title";
        String testContent = "Test Content";
        message.setTitle(testTitle);
        message.setContent(testContent);
        MessageDTO messageInDatabase = databaseutil.post(message);
        UUID id = messageInDatabase.getId();


        // Act
        mockMvc.perform(delete("/producer/message/" + id))

                // Assert
                .andExpect(status().isOk());

        assertThat(databaseutil.exists(id)).isFalse();
    }
}
