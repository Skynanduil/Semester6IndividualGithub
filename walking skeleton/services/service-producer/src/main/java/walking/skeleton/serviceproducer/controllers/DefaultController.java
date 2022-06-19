package walking.skeleton.serviceproducer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class DefaultController{

    private final static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value= "${kafka.topic.name}")
    private String topicName;

    @GetMapping("/static")
    public ResponseEntity<String> getStaticResponse(){
        return new ResponseEntity<>("This is a static response from the api", HttpStatus.OK);
    }

    @PostMapping("/produce")
    public ResponseEntity<Void> produceMessage(@RequestParam String message){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String,String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result){
                logger.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
