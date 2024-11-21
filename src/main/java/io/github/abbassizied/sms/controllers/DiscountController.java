package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Discount;
import io.github.abbassizied.sms.services.DiscountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discount")
public class DiscountController {

	private final DiscountService discountService;
    
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    } 
    

    @GetMapping("/list")
    public String listDiscounts(Model model) {
        model.addAttribute("discounts", discountService.listDiscounts());
        return "discount/listDiscounts";
    }

    @GetMapping("/add")
    public String addDiscountForm(Model model) {
        model.addAttribute("discount", new Discount());
        return "discount/addDiscount";
    }

    @PostMapping("/add")
    public String addDiscount(@ModelAttribute Discount discount) {
        discountService.saveDiscount(discount);
        return "redirect:/discount/list";
    }

    @GetMapping("/show/{id}")
    public String showDiscount(@PathVariable("id") long id, Model model) {
        model.addAttribute("discount", discountService.getDiscountById(id));
        return "discount/showDiscount";
    }

    @GetMapping("/update/{id}")
    public String updateDiscountForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("discount", discountService.getDiscountById(id));
        return "discount/updateDiscount";
    }

    @PostMapping("/update/{id}")
    public String updateDiscount(@PathVariable("id") long id, @ModelAttribute Discount discount) {
        //discount.setId(id);
        discountService.saveDiscount(discount);
        return "redirect:/discount/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiscount(@PathVariable("id") long id) {
        discountService.deleteDiscountById(id);
        return "redirect:/discount/list";
    }
}
