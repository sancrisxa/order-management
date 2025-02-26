package br.com.sancrisxa.order_management.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(String orderNumber, List<ItemDto> items, BigDecimal totalValue) {
}
