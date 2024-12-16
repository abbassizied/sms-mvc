package io.github.abbassizied.sms.dtos;

import java.util.List;

public record PurchaseDTO(Long supplierId, Double totalAmount, List<PurchaseItemDTO> purchaseItems) {

}
