package walking.skeleton.serviceconsumer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class DefaultController {

    private final static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Value(value= "${kafka.topic.name}")
    private String topicName;

    @Value(value= "${kafka.topic.group}")
    private String groupId;

    @KafkaListener(topics= "producer_topic", groupId = "skeleton")
    public void listenGroupSkeleton(String message){
        logger.info("Received Message in group: " + message);
    }

    @GetMapping("/static")
    public ResponseEntity<String> getStaticResponse(){
        return new ResponseEntity<String>("This is a static response from the api", HttpStatus.OK);
    }
}
