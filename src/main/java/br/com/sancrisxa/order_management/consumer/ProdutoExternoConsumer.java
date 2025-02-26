package br.com.sancrisxa.order_management.consumer;

import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.service.OrderService;
import br.com.sancrisxa.order_management.util.OrderDtoConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
public class ProdutoExternoConsumer {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = { "produto-externo-A"})
    public void receive(Message payload) throws IOException {

        try {
            OrderDto orderDto = OrderDtoConverter.convertMessageToOrderDto(payload);
            orderService.createOrder(orderDto);
            System.out.println(orderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
