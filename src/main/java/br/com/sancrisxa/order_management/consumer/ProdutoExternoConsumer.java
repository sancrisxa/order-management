package br.com.sancrisxa.order_management.consumer;

import br.com.sancrisxa.order_management.dto.ConsumerDto;
import br.com.sancrisxa.order_management.facade.OrderFacadeImpl;
import br.com.sancrisxa.order_management.service.OrderService;
import br.com.sancrisxa.order_management.util.ConsumerDtoConverter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ProdutoExternoConsumer {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFacadeImpl gerenciarOrderFacade;

    @RabbitListener(queues = { "produto-externo-A"})
    public void receiveMessage(Message message) throws IOException {

        try {
            ConsumerDto consumerDto = ConsumerDtoConverter.convertMessageToOrderDto(message);
            System.out.println("Received: " + consumerDto);
            gerenciarOrderFacade.gerenciarOrder(consumerDto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
