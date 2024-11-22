package io.github.abbassizied.sms.controllers;
 
import io.github.abbassizied.sms.entities.Supplier;
import io.github.abbassizied.sms.services.SupplierService; 
import io.github.abbassizied.sms.forms.SupplierForm;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*; 
   
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid; 
 

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
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
    model.addAttribute("genders", Arrays.asList("Male", "Female", "Other"));
       model.addAttribute("supplierForm", new SupplierForm()); 
        return "supplier/addSupplier";
    }

    @PostMapping("/add") 
    public String addSupplier(@Valid @ModelAttribute("supplierForm") SupplierForm supplierForm,
            BindingResult bindingResult, Model model) { 
    	System.out.println("Raw form data: " + supplierForm);
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            System.out.println("Form data: " + supplierForm);
            // Reload form with validation errors
            model.addAttribute("genders", Arrays.asList("Male", "Female", "Other"));
            return "supplier/addSupplier";
        }

        // Convert SupplierForm to Supplier entity (assuming you have a converter or manually map fields)
        Supplier supplier = new Supplier();
        supplier.setCompanyName(supplierForm.getCompanyName());
        supplier.setLogoUrl(supplierForm.getLogoUrl());
        supplier.setEmail(supplierForm.getEmail());
        supplier.setPhone(supplierForm.getPhone());
        supplier.setAddress(supplierForm.getAddress());  
        
        System.out.println("Supplier: " + supplier); 
        
        return "redirect:/supplier/list";
    }

    @GetMapping("/show/{id}")
    public String showSupplier(@PathVariable("id") long id, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(id));
        return "supplier/showSupplier";
    }

    @GetMapping("/update/{id}")
    public String updateSupplierForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(id));
        return "supplier/updateSupplier";
    }

    @PostMapping("/update/{id}")
    public String updateSupplier(@PathVariable("id") long id, @ModelAttribute Supplier supplier) {
        supplier.setId(id);
        supplierService.saveSupplier(supplier);
        return "redirect:/supplier/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") long id) {
        supplierService.deleteSupplierById(id);
        return "redirect:/supplier/list";
    }
    
}
