package io.github.abbassizied.sms.dtos;

public record CartItemDTO(Long productId, String name, Double price, Integer quantity, String subtotal) {

}
