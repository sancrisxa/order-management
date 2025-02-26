package br.com.sancrisxa.order_management.util;

import br.com.sancrisxa.order_management.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import java.io.IOException;

public class OrderDtoConverter {

    public static OrderDto convertMessageToOrderDto(Message message) throws IOException {
        byte[] body = message.getBody();
        String json = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, OrderDto.class);
    }
}