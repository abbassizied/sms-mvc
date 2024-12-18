package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import io.github.abbassizied.sms.services.CompanySettingService;
import io.github.abbassizied.sms.services.SupplierService;

@Controller
public class AboutUsController {

    private final CompanySettingService companySettingService;    
	private final SupplierService supplierService;
 
    public AboutUsController( CompanySettingService companySettingService,
    		                  SupplierService supplierService) {
        this.companySettingService = companySettingService;
        this.supplierService = supplierService;
    }
	
	
	@GetMapping("/about")
	public String aboutUsPage(Model model) {
		model.addAttribute("suppliers", supplierService.listSuppliers());
        // Load the existing setting or create a new one if none exists 
        model.addAttribute("companySetting", companySettingService.getOrCreateSetting());
		
		return "frontoffice/about";
	}

}