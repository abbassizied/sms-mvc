package io.github.abbassizied.sms.dtos;

public record PurchaseItemDTO(Long productId, String productName, Integer quantity, Double price,
		Double subTotalPrice) {
}
