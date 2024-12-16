package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.dtos.PurchaseDTO;
import io.github.abbassizied.sms.dtos.PurchaseItemDTO;
import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.entities.Purchase;
import io.github.abbassizied.sms.entities.PurchaseItem;
import io.github.abbassizied.sms.entities.Supplier;
import io.github.abbassizied.sms.services.PurchaseService;
import io.github.abbassizied.sms.services.ProductService;
import io.github.abbassizied.sms.services.SupplierService;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;
	private final ProductService productService;
	private final SupplierService supplierService;
	
    public PurchaseController(PurchaseService purchaseService,
    		                  ProductService productService, 
    		                  SupplierService supplierService) {
        this.purchaseService = purchaseService;
		this.productService = productService;
		this.supplierService = supplierService;
    } 
    
	// Redirect root URL to the paginated list view
	@GetMapping
	public String index() { 
		return "redirect:purchases/list";
	}        

    @GetMapping("/list")
    public String listPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.listPurchases());
        return "purchase/listPurchases";
    }

    
    @GetMapping("/add")
    public String addPurchaseForm(Model model) {
    	List<Supplier> suppliers = supplierService.listSuppliers();
    	model.addAttribute("suppliers", suppliers);
        model.addAttribute("purchase", new Purchase());
        return "purchase/addPurchase";
    }
    
    
	@GetMapping("/get-products/{supplierId}")
	public String getProductsBySupplier(@PathVariable Long supplierId, Model model) {
		// Fetch products for the selected supplier
		List<Product> products = productService.listProducts().stream()
				.filter(product -> product.getSupplier().getId().equals(supplierId)).toList();

		model.addAttribute("products", products);
		return "purchase/add-item :: addItemFragment";
	}    

	@PostMapping("/add")
	public ResponseEntity<String> addPurchase(@RequestBody PurchaseDTO purchaseRequest) {

		System.out.println("Received purchase data: " + purchaseRequest);
		Purchase purchase = new Purchase();
		List<PurchaseItem> newPurchaseItemList = new ArrayList<>();

		Supplier supplier = supplierService.getSupplierById(purchaseRequest.supplierId());
		System.out.println(supplier);
		
		for(PurchaseItemDTO item : purchaseRequest.purchaseItems()) {
			PurchaseItem newItem = new PurchaseItem();
			Product product = productService.getProductById(item.productId());
			newItem.setProduct(product);
			newItem.setQuantity(item.quantity());
			newItem.setSubTotalPrice(item.subTotalPrice());
			
			newItem.setPurchase(purchase);  // Link the purchase to the item
			
			newPurchaseItemList.add(newItem); 
		}
		
		purchase.setSupplier(supplier);
		purchase.setTotalAmount(purchaseRequest.totalAmount());
		purchase.setPurchaseItems(newPurchaseItemList);
		 
		try { 
			 purchaseService.savePurchase(purchase);
			return ResponseEntity.ok("Success"); // Respond with success

		} catch (Exception e) {
			System.out.println("error message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

    @GetMapping("/show/{id}")
    public String showPurchase(@PathVariable("id") long id, Model model) {
        model.addAttribute("purchase", purchaseService.getPurchaseById(id));
        return "purchase/showPurchase";
    }
    
    
/*
    @GetMapping("/update/{id}")
    public String updatePurchaseForm(@PathVariable("id") long id, Model model) {
        //model.addAttribute("purchase", purchaseService.getPurchaseById(id));
        return "purchase/updatePurchase";
    }

    @PostMapping("/update/{id}")
    public String updatePurchase(@PathVariable("id") long id, @ModelAttribute Purchase purchase) {
        //purchase.setId(id);
        //purchaseService.savePurchase(purchase);
        return "redirect:/purchase/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable("id") long id) {
        //purchaseService.deletePurchaseById(id);
        return "redirect:/purchase/list";
    }
*/    
    
    
}

