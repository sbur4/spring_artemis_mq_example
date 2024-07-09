package com.epam.example.message.sender;

import com.epam.example.message.dto.MessageDto;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class MessageTextSender {
    @Setter
    @Autowired
    private JmsTemplate jmsTemplate;

    @Setter
    @Autowired
    private MessageConverter converter;


    public void sendMessageMessage(String destination, MessageDto msg) {
        jmsTemplate.convertAndSend(destination, msg);
    }

    @SneakyThrows
    public MessageDto sendAndReceiveMessage(String destination, MessageDto messageToBeSent) {
        messageToBeSent.setContent("aaaa");
        Message received = jmsTemplate.sendAndReceive(destination, session -> {
            ObjectMessage message = session.createObjectMessage(messageToBeSent);
            return message;
        });

        MessageDto response = (MessageDto) converter.fromMessage(received);

        return response;
    }
}
