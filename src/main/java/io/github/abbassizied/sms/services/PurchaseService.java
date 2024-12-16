package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.Purchase;

public interface PurchaseService {
	Purchase getPurchaseById(Long id);
	Purchase savePurchase(Purchase purchase);
	List<Purchase> listPurchases();
}
