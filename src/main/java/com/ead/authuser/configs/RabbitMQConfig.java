package com.ead.authuser.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        var template = new RabbitTemplate(cachingConnectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter);
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public FanoutExchange fanoutUserEvent(@Value("${ead.broker.exchange.userEvent}") String exchangeUserEvent) {
        return new FanoutExchange(exchangeUserEvent);
    }
}
