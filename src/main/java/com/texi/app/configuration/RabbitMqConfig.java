package com.texi.app.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    @Value("${amqp.exchanges.posts}")
    private String postsExchange;

    @Value("${amqp.keys.posts}")
    private String postsKey;

    @Value("${amqp.queues.posts}")
    protected String postsQueue;

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public DirectExchange postsExchange() {
        return new DirectExchange(this.postsExchange);
    }

    @Bean
    public Queue postsQueue() {
        return new Queue(this.postsQueue);
    }

    @Bean
    public Binding postsQueueBinding() {
        return BindingBuilder.bind(postsQueue()).to(postsExchange()).with(postsKey);
    }
}
