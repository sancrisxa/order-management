package br.com.sancrisxa.order_management.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable("queue-order")
                .withArgument("x-dead-letter-exchange", "order-exchange.dlx")
                .withArgument("x-dead-letter-routing-key", "queue-order.dlq") // nao precisa ser o nome da queue, mas é comum para direct
                .withArgument("x-message-ttl", 5000)
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable("queue-order.dlq").build();
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("order-exchange.dlx");
    }

    @Bean
    Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("queue-order.dlq");
    }
}
