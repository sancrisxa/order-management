package br.com.sancrisxa.order_management.service;

import br.com.sancrisxa.order_management.Repository.OrderRepository;
import br.com.sancrisxa.order_management.domain.Order;
import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.exceptions.OrderAlreadyExistsException;
import br.com.sancrisxa.order_management.exceptions.OrderNotFoundException;
import br.com.sancrisxa.order_management.util.OrderConverter;
import br.com.sancrisxa.order_management.util.OrderDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @CacheEvict(value = "orders_manegemnt_methods", allEntries = true)
    public Order createOrder(OrderDto orderDto) {

        if (orderRepository.existsByOrderNumber(orderDto.orderNumber())) {
            throw new OrderAlreadyExistsException("Order with number " + orderDto.orderNumber() + " already exists.");
        }

        Order orderDtoToOrder = OrderConverter.convertOrderDtoToOrder(orderDto);
        Order order = new Order();
        order.setOrderNumber(orderDtoToOrder.getOrderNumber());
        order.setStatus(orderDtoToOrder.getStatus());
        order.setItems(orderDtoToOrder.getItems());
        order.setTotalValue(orderDtoToOrder.getTotalValue());
        return orderRepository.save(order);
    }

    @Cacheable(value = "orders_manegemnt_methods", key = "#orderNumber")
    public OrderDto getOrder(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null) {
            throw new OrderNotFoundException("Oder not found: " + orderNumber);
        }
        return OrderDtoConverter.convertOrderToOrderDto(order);
    }

    @Cacheable(value = "orders_manegemnt_methods")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
