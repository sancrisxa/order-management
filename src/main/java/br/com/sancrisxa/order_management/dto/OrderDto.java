package br.com.sancrisxa.order_management.dto;

import br.com.sancrisxa.order_management.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(String orderNumber, OrderStatus status, List<ItemDto> items, BigDecimal totalValue) {
}
