package br.com.sancrisxa.order_management.util;

import br.com.sancrisxa.order_management.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public class CalculateTotalPrice {
    public static BigDecimal calculateTotalPrice(List<ItemDto> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ItemDto item : items) {
            BigDecimal itemTotal = item.price().multiply(BigDecimal.valueOf(item.quantity()));
            totalPrice = totalPrice.add(itemTotal);
        }
        return totalPrice;
    }
}
