package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Supplier;
import io.github.abbassizied.sms.exceptions.SupplierNotFoundException;
import io.github.abbassizied.sms.repositories.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository; // interface

	@Override
	public List<Supplier> listSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier getSupplierById(long id) {
		return supplierRepository.findById(id)
				.orElseThrow(() -> new SupplierNotFoundException("Invalid supplier Id:" + id));
	}

	@Override
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplierById(long id) {
		Supplier supplier = supplierRepository.findById(id)
				.orElseThrow(() -> new SupplierNotFoundException("Invalid supplier Id:" + id));
		supplierRepository.delete(supplier);
	}

}
