package kaya.fhict.servicenotifications.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaReceiver {

    @KafkaListener(topics = "${mp.messaging.incoming.test-in.topic}")
    public void listen(String message){
        System.out.println("Received the message: " + message);
    }
}
