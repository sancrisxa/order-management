package br.com.sancrisxa.order_management.api;

import br.com.sancrisxa.order_management.dto.ConsumerDto;
import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping
    public OrderDto createOrder(@RequestBody ConsumerDto consumerDto) {
        return orderFacade.createOrder(consumerDto);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable String id) {
        return orderFacade.getOrder(id);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderFacade.getAllOrders();
    }
}
