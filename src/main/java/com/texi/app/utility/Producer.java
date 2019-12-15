package com.texi.app.utility;

import com.texi.app.domain.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amqp.exchanges.posts}")
    private String exchange;

    @Value("${amqp.keys.posts}")
    private String routingKey;

    public void produce(Payload content) {
        logger.info("sending {} to {} exchange", content, exchange);
        rabbitTemplate.convertAndSend(exchange, routingKey, content);
    }
}
