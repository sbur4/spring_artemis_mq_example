package com.epam.example.message.config;

import lombok.SneakyThrows;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

@EnableJms
@Configuration
public class ActiveMQConfig {
    private static final String QUEUE = "jms.message.queue";

    @Value("${spring.activemq.broker-url}")
    String BROKER_URL;
//    @Value("${spring.activemq.user}")
    @Value("${spring.artemis.user}")
    String BROKER_USERNAME;
    @Value("${spring.artemis.password}")
//    @Value("${spring.activemq.password}")
    String BROKER_PASSWORD;

    @SneakyThrows
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUser(BROKER_PASSWORD);
        return connectionFactory;
    }

    @Bean
    MessageConverter converter() {
        return new SimpleMessageConverter();
    }
}
