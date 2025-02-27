package br.com.sancrisxa.order_management.util;

import br.com.sancrisxa.order_management.domain.Item;
import br.com.sancrisxa.order_management.domain.Order;
import br.com.sancrisxa.order_management.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {

    public static Order convertOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(orderDto.orderNumber());
        order.setTotalValue(orderDto.totalValue());
        order.setStatus(orderDto.status());

        List<Item> items = orderDto.items().stream()
                .map(dtoItem -> {
                    Item item = new Item();
                    item.setName(dtoItem.name());
                    item.setDescription(dtoItem.description());
                    item.setQuantity(dtoItem.quantity());
                    item.setPrice(dtoItem.price());
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(items);
        return order;
    }
}
