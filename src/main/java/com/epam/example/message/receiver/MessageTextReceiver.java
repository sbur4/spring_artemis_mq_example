package com.epam.example.message.receiver;

import com.epam.example.message.dto.MessageDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageTextReceiver {
//    @Setter
//    @Autowired
//    private JmsTemplate jmsTemplate;
    @JmsListener(destination = "jms.message.queue")
    public void receiveMessage(MessageDto msg) {
        System.out.println("Received " + msg);
    }

    @JmsListener(destination = "jms.message.queue2")
    public MessageDto receiveMessage2(MessageDto msg) {
        System.out.println("Received " + msg);
        return msg;
    }
}
