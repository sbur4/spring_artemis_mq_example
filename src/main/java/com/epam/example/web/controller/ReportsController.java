package com.epam.example.web.controller;

import com.epam.example.message.dto.MessageDto;
import com.epam.example.message.receiver.MessageTextReceiver;
import com.epam.example.message.sender.MessageTextSender;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class ReportsController {
    @Setter
    @Autowired
    private MessageTextSender testSender;
    @Setter
    @Autowired
    private MessageTextReceiver testReceiver;
    @Setter
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(value = "/send/{txt}")
    public String send(@PathVariable String txt) {
        testSender.sendMessageMessage("jms.message.queue", MessageDto.builder()
                .id(UUID.randomUUID().getLeastSignificantBits())
                .content(txt)
                .date(new Date())
                .build());
        return txt;
    }

    @GetMapping(value = "/sendTo/{txt}")
    public ResponseEntity<MessageDto> sendTo(@PathVariable String txt) {
        MessageDto message = MessageDto.builder()
                .id(UUID.randomUUID().getLeastSignificantBits())
                .content(txt)
                .date(new Date())
                .build();
        MessageDto receivedMessage = testSender.sendAndReceiveMessage("jms.message.queue2", message);
        return new ResponseEntity<>(receivedMessage, HttpStatus.OK);
    }
}