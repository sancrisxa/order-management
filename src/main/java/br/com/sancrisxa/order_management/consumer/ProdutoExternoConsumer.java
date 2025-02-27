package br.com.sancrisxa.order_management.consumer;

import br.com.sancrisxa.order_management.dto.ConsumerDto;
import br.com.sancrisxa.order_management.exceptions.OrderAlreadyExistsException;
import br.com.sancrisxa.order_management.facade.OrderFacadeImpl;
import br.com.sancrisxa.order_management.service.OrderService;
import br.com.sancrisxa.order_management.util.ConsumerDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ProdutoExternoConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoExternoConsumer.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFacadeImpl gerenciarOrderFacade;

    @RabbitListener(queues = { "produto-externo-A"})
    public void receiveMessage(Message message) throws IOException {

        try {
            ConsumerDto consumerDto = ConsumerDtoConverter.convertMessageToOrderDto(message);
            logger.info("Received message with id: {}, routingKey: {}, consumerDto: {}", message.getMessageProperties().getMessageId(), message.getMessageProperties().getReceivedRoutingKey(), consumerDto);
            gerenciarOrderFacade.gerenciarOrder(consumerDto);

        }  catch (IOException e) {
            logger.error("IOException processing message with id: {}, routingKey: {}", message.getMessageProperties().getMessageId(), message.getMessageProperties().getReceivedRoutingKey(), e);
            throw new RuntimeException("IOException during message processing", e);
        } catch (RuntimeException e) {
            logger.error("RuntimeException processing message with id: {}, routingKey: {}", message.getMessageProperties().getMessageId(), message.getMessageProperties().getReceivedRoutingKey(), e);
            throw new RuntimeException("RuntimeException during message processing", e);
        } catch (Exception e){
            logger.error("Exception processing message with id: {}, routingKey: {}", message.getMessageProperties().getMessageId(), message.getMessageProperties().getReceivedRoutingKey(), e);
            throw new RuntimeException("Exception during message processing", e);
        }
    }
}
