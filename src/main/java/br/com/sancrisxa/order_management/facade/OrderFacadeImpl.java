package br.com.sancrisxa.order_management.facade;


import br.com.sancrisxa.order_management.domain.Order;
import br.com.sancrisxa.order_management.dto.ConsumerDto;
import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.service.OrderService;
import br.com.sancrisxa.order_management.util.CalculateTotalPrice;
import br.com.sancrisxa.order_management.util.OrderDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    OrderService orderService;

    public void gerenciarOrder(ConsumerDto order) {

        BigDecimal totalPrice = CalculateTotalPrice.calculateTotalPrice(order.items());

        OrderDto orderDto = new OrderDto(order.orderNumber(), order.status(), order.items(), totalPrice);

        orderService.createOrder(orderDto);

    }

    @Override
    public OrderDto createOrder(ConsumerDto consumerDto) {
        BigDecimal totalPrice = CalculateTotalPrice.calculateTotalPrice(consumerDto.items());

        OrderDto newOrderDto = new OrderDto(consumerDto.orderNumber(), consumerDto.status(), consumerDto.items(), totalPrice);

        Order order = orderService.createOrder(newOrderDto);

        return OrderDtoConverter.convertOrderToOrderDto(order);
    }

    @Override
    public OrderDto getOrder(String orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public List<OrderDto> getAllOrders() {

        List<Order> allOrders = orderService.getAllOrders();

        return allOrders.stream()
                .map(OrderDtoConverter::convertOrderToOrderDto)
                .collect(Collectors.toList());
    }
}
