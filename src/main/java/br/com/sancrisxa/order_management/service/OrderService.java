package br.com.sancrisxa.order_management.service;

import br.com.sancrisxa.order_management.Repository.OrderRepository;
import br.com.sancrisxa.order_management.domain.Item;
import br.com.sancrisxa.order_management.domain.Order;
import br.com.sancrisxa.order_management.dto.ItemDto;
import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.util.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderDto orderDto) {
        Order orderDtoToOrder = OrderConverter.convertOrderDtoToOrder(orderDto);
        Order order = new Order();
        order.setOrderNumber(orderDtoToOrder.getOrderNumber());
        order.setItems(orderDtoToOrder.getItems());
        order.setTotalValue(orderDtoToOrder.getTotalValue());
        return orderRepository.save(order);
    }

    public Order getOrder(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
