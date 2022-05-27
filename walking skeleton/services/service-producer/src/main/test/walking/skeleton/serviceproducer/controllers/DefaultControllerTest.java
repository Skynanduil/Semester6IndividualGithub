package walking.skeleton.serviceproducer.controllers;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DefaultControllerTest {
    @Test
    public void GetStaticResponse_Should_ReturnString(){
        // Arrange
        String expected = "This is a static response from the api";
        DefaultController defaultController = new DefaultController();

        // Act
        ResponseEntity<String> result = defaultController.getStaticResponse();

        // Assert
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isEqualTo(expected);
    }
}
