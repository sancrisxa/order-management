package br.com.sancrisxa.order_management.api;

import br.com.sancrisxa.order_management.dto.ConsumerDto;
import br.com.sancrisxa.order_management.dto.OrderDto;
import br.com.sancrisxa.order_management.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody ConsumerDto consumerDto) {
        OrderDto createdOrder = orderFacade.createOrder(consumerDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) {
        OrderDto order = orderFacade.getOrder(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderFacade.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
