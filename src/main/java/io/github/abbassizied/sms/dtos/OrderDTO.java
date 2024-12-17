package io.github.abbassizied.sms.dtos;

import java.util.List;

public record OrderDTO(List<CartItemDTO> items, String totalAmount) {

}
