package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/sales")
public class SaleController {
  /*
   * 
   * 
   * 
   * 
   *  hedi zeyda : to be removed
   * 
   * 
   * 
   */
    @GetMapping
    public String listSales(Model model) {    
        return "sale/listSales";  // Return the view for sale list
    }
    
}
