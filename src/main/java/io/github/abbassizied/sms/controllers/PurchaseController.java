package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Purchase;
import io.github.abbassizied.sms.services.PurchaseService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    } 
    
	// Redirect root URL to the paginated list view
	@GetMapping
	public String index() { 
		return "redirect:purchases/list";
	}        

    @GetMapping("/list")
    public String listPurchases(Model model) {
       // model.addAttribute("purchases", purchaseService.listPurchases());
        return "purchase/listPurchases";
    }

    @GetMapping("/add")
    public String addPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase/addPurchase";
    }

    @PostMapping("/add")
    public String addPurchase(@ModelAttribute Purchase purchase) {
        //purchaseService.savePurchase(purchase);
        return "redirect:/purchase/list";
    }

    @GetMapping("/show/{id}")
    public String showPurchase(@PathVariable("id") long id, Model model) {
        //model.addAttribute("purchase", purchaseService.getPurchaseById(id));
        return "purchase/showPurchase";
    }

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
    
}

