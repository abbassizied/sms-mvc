package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Purchase;
import io.github.abbassizied.sms.exceptions.PurchaseNotFoundException;
import io.github.abbassizied.sms.repositories.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
	
	@Override
	public Purchase savePurchase(Purchase purchase) { 
		return purchaseRepository.save(purchase);
	}

	@Override
	public List<Purchase> listPurchases() { 
		return purchaseRepository.findAll();
	}

	@Override
	public Purchase getPurchaseById(Long id) { 
		return purchaseRepository.findById(id).map(purchase -> {
			return purchase;
		}).orElseThrow(() -> new PurchaseNotFoundException("Purchase with Id " + id + " was not found"));
	}  
}
