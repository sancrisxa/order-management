package br.com.sancrisxa.order_management.dto;

import java.math.BigDecimal;

public record ItemDto(String name, String description, Integer quantity, BigDecimal price) {
}
