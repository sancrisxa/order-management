package br.com.sancrisxa.order_management.service;

import br.com.sancrisxa.order_management.Repository.OrderRepository;
import br.com.sancrisxa.order_management.domain.Order;
import br.com.sancrisxa.order_management.domain.OrderStatus;
import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.exceptions.OrderAlreadyExistsException;
import br.com.sancrisxa.order_management.util.OrderConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderDto orderDto;
    private Order order;

    @BeforeEach
    void setUp() {
        orderDto = new OrderDto("123", OrderStatus.PENDING, new ArrayList<>(), new BigDecimal("100.0"));
        order = OrderConverter.convertOrderDtoToOrder(orderDto);
    }

    @Test
    void createOrder_shouldCreateOrder_whenOrderNumberDoesNotExist() {
        when(orderRepository.existsByOrderNumber(orderDto.orderNumber())).thenReturn(false);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(orderDto);

        assertNotNull(createdOrder);
        assertEquals(order.getOrderNumber(), createdOrder.getOrderNumber());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void createOrder_shouldThrowOrderAlreadyExistsException_whenOrderNumberExists() {
        when(orderRepository.existsByOrderNumber(orderDto.orderNumber())).thenReturn(true);

        assertThrows(OrderAlreadyExistsException.class, () -> orderService.createOrder(orderDto));
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void getOrder_shouldReturnOrderDto_whenOrderExists() {
        when(orderRepository.findByOrderNumber(orderDto.orderNumber())).thenReturn(order);

        OrderDto result = orderService.getOrder(orderDto.orderNumber());

        assertNotNull(result);
        assertEquals(orderDto, result);
    }

    @Test
    void getOrder_shouldReturnNull_whenOrderDoesNotExist() {
        when(orderRepository.findByOrderNumber(orderDto.orderNumber())).thenReturn(null);

        OrderDto result = orderService.getOrder(orderDto.orderNumber());

        assertNull(result);
    }

    @Test
    void getAllOrders_shouldReturnListOfOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order, result.get(0));
    }

    @Test
    void getAllOrders_shouldReturnEmptyList_whenNoOrdersExist() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        List<Order> result = orderService.getAllOrders();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

