package br.com.sancrisxa.order_management.facade;

import br.com.sancrisxa.order_management.dto.ConsumerDto;
import br.com.sancrisxa.order_management.dto.OrderDto;

import java.util.List;

public interface OrderFacade {
    OrderDto createOrder(ConsumerDto consumerDto);
    OrderDto getOrder(String orderId);
    List<OrderDto> getAllOrders();
}
