package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Supplier;
import io.github.abbassizied.sms.services.StorageService;
import io.github.abbassizied.sms.services.SupplierService;
import io.github.abbassizied.sms.utils.Alert;
import io.github.abbassizied.sms.forms.OnCreate;
import io.github.abbassizied.sms.forms.OnUpdate;
import io.github.abbassizied.sms.forms.SupplierForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private MessageSource messageSource;
    
	private final SupplierService supplierService;
	private final StorageService storageService;

	public SupplierController(SupplierService supplierService, StorageService storageService) {
		this.supplierService = supplierService;
		this.storageService = storageService;
	}

	// Redirect root URL to the paginated list view
	@GetMapping
	public String index() {
		return "redirect:suppliers/list";
	}

	@GetMapping("/list")
	public String listSuppliers(Model model) {
		model.addAttribute("suppliers", supplierService.listSuppliers());
		return "supplier/listSuppliers";
	}

	@GetMapping("/add")
	public String addSupplierForm(Model model) {
		// model.addAttribute("genders", Arrays.asList("Male", "Female", "Other"));
		model.addAttribute("supplierForm", new SupplierForm());
		return "supplier/addSupplier";
	}

	@PostMapping("/add")
	public String addSupplier(@Validated(OnCreate.class) @ModelAttribute("supplierForm") SupplierForm supplierForm,
			BindingResult bindingResult, 
			Model model,
            RedirectAttributes redirectAttributes) {
		// System.out.println("Raw form data: " + supplierForm);
		if (bindingResult.hasErrors()) {
			// System.out.println("Validation errors: " + bindingResult.getAllErrors());
			//System.out.println("Form data: " + supplierForm);
			// Reload form with validation errors
			// model.addAttribute("genders", Arrays.asList("Male", "Female", "Other"));  
			return "supplier/addSupplier";
		}
		
		String fileName = storageService.storeSingleFile(supplierForm.getLogoUrl());
		// Convert SupplierForm to Supplier entity 
		Supplier supplier = new Supplier();
		supplier.setName(supplierForm.getName());
		supplier.setLogoUrl(fileName); // be carefull
		supplier.setEmail(supplierForm.getEmail());
		supplier.setPhone(supplierForm.getPhone());
		supplier.setAddress(supplierForm.getAddress());

		System.out.println("Supplier: " + supplier);
		try {
			supplierService.saveSupplier(supplier); 
	        redirectAttributes.addFlashAttribute("alert", 
				    new Alert("success", messageSource.getMessage("success.create", null, LocaleContextHolder.getLocale())));
		} catch (Exception e) {
			storageService.delete(fileName);
	        // Add an error alert 
	        redirectAttributes.addFlashAttribute("alert", 
				    new Alert("danger", messageSource.getMessage("error.server", null, LocaleContextHolder.getLocale())));
		}
		return "redirect:/suppliers/list"; // Redirect to supplier list
	}

	@GetMapping("/show/{id}")
	public String showSupplier(@PathVariable("id") long id, Model model) {
		model.addAttribute("supplier", supplierService.getSupplierById(id));
		return "supplier/showSupplier";
	}

	@GetMapping("/update/{id}")
	public String updateSupplierForm(@PathVariable("id") long id,Model model) {

		Supplier supplier = supplierService.getSupplierById(id);
		
	    // Convert Supplier entity to SupplierForm  
	    SupplierForm supplierForm = new SupplierForm();
	    supplierForm.setId(supplier.getId());
	    supplierForm.setName(supplier.getName());
	    supplierForm.setLogoUrl(null);  // You don't need to set the logoUrl from the supplier here as it's a file input in the form
	    supplierForm.setEmail(supplier.getEmail());
	    supplierForm.setPhone(supplier.getPhone());
	    supplierForm.setAddress(supplier.getAddress());    
	    
	    // Add supplierForm to model
	    model.addAttribute("supplierForm", supplierForm);	 
		return "supplier/updateSupplier";
	}

	@PostMapping("/update/{id}")
	public String updateSupplier(@PathVariable("id") long id, 
			@Validated(OnUpdate.class)  @ModelAttribute("supplierForm") SupplierForm supplierForm,
			BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
		 
		if (bindingResult.hasErrors()) {
			return "supplier/updateSupplier";
		}		
		// Fetch the existing supplier entity
	    Supplier supplier = supplierService.getSupplierById(id);

	    // Handle the logo file (only update if a new file is uploaded)
	    if (!supplierForm.getLogoUrl().isEmpty()) {
	    	// delete old existing image from storage
	    	storageService.delete(supplier.getLogoUrl()); 
	    	// upload the new image
	        String fileName = storageService.storeSingleFile(supplierForm.getLogoUrl());
	        supplier.setLogoUrl(fileName); // Update logo
	    }
	
	    // Update other fields
	    supplier.setName(supplierForm.getName());
	    supplier.setEmail(supplierForm.getEmail());
	    supplier.setPhone(supplierForm.getPhone());
	    supplier.setAddress(supplierForm.getAddress());
	    
		try {
			// Save updated supplier
			supplierService.saveSupplier(supplier);
			/*
		    if (true) { // Replace with any condition you want to simulate
		        throw new IllegalArgumentException("Simulated exception");
		    }
			*/
			redirectAttributes.addFlashAttribute("alert", new Alert("success",
					messageSource.getMessage("success.update", null, LocaleContextHolder.getLocale())));
		} catch (Exception e) {
			// System.out.println("***********************************************************");
			// Add an error alert
			redirectAttributes.addFlashAttribute("alert", new Alert("danger",
					messageSource.getMessage("error.server", null, LocaleContextHolder.getLocale())));
		}
		return "redirect:/suppliers/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteSupplier(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		Supplier supplier = supplierService.getSupplierById(id);    	
		// Delete old existing image from storage
    	storageService.delete(supplier.getLogoUrl());
    	// Then, delete supplier
		try {
			supplierService.deleteSupplierById(id);
	        redirectAttributes.addFlashAttribute("alert", 
				    new Alert("success", messageSource.getMessage("success.delete", null, LocaleContextHolder.getLocale())));
		} catch (Exception e) { 
	        // Add an error alert 
	        redirectAttributes.addFlashAttribute("alert", 
				    new Alert("danger", messageSource.getMessage("error.server", null, LocaleContextHolder.getLocale())));
		}    	
		return "redirect:/suppliers/list";
	}

}
