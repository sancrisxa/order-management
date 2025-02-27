package br.com.sancrisxa.order_management.dto;

import br.com.sancrisxa.order_management.domain.OrderStatus;

import java.util.List;

public record ConsumerDto(String orderNumber, OrderStatus status, List<ItemDto> items) {
}
