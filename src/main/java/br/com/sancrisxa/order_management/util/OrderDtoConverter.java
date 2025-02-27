package br.com.sancrisxa.order_management.util;

import br.com.sancrisxa.order_management.domain.Order;
import br.com.sancrisxa.order_management.dto.ItemDto;
import br.com.sancrisxa.order_management.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDtoConverter {
    public static OrderDto convertOrderToOrderDto(Order order) {
        if (order == null) {
            return null;
        }

        List<ItemDto> itemDtos = order.getItems().stream()
                .map(item -> new ItemDto(item.getName(), item.getDescription(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());

        return new OrderDto(order.getOrderNumber(), order.getStatus(), itemDtos, order.getTotalValue());
    }
}
