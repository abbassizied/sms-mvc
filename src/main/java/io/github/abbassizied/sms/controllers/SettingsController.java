package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/settings")
public class SettingsController {

    @GetMapping
    public String manageSettings(Model model) {    
        return "settings/settings";  // Return the view for settings management
    }
    
}
