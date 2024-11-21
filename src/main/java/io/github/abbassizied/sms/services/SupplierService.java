package io.github.abbassizied.sms.services;

import java.util.List;
 
import io.github.abbassizied.sms.entities.Supplier;

public interface SupplierService {
	
	public List<Supplier> listSuppliers();

	public Supplier getSupplierById(long id);

	public Supplier saveSupplier(Supplier supplier);

	public void deleteSupplierById(long id);
}
