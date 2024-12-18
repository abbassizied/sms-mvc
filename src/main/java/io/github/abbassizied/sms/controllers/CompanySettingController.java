package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.CompanySetting;
import io.github.abbassizied.sms.services.CompanySettingService;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class CompanySettingController {

    private final CompanySettingService companySettingService;
 
    public CompanySettingController(CompanySettingService companySettingService) {
        this.companySettingService = companySettingService;
    }

    /**
     * Display the settings form, either for creating or updating the CompanySetting.
     */
    @GetMapping
    public String manageSettings(Model model) {
        // Load the existing setting or create a new one if none exists
        CompanySetting companySetting = companySettingService.getOrCreateSetting();
        model.addAttribute("companySetting", companySetting);
        return "settings/settings";
    }

    /**
     * Handle form submission for creating or updating the CompanySetting.
     */
    @PostMapping
    public String saveSettings(CompanySetting companySetting) {
        companySettingService.saveOrUpdate(companySetting);
        return "settings/settings";
    }
}
