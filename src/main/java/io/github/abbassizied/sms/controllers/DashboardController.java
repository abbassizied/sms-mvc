package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/dashboard")
public class DashboardController {

    // This method will capture the "lang" path variable and load the appropriate locale
    @GetMapping()
    public String dashboard(@PathVariable String lang, Model model) {
        // Optional: You can log or validate the language, or just let UrlLocaleResolver do it
        System.out.println("Locale selected: " + lang); // for debugging
        
        // Return the dashboard view
        return "dashboard/dashboard";
    }

    // Super Admin Dashboard
    @GetMapping("/superadmin")
    public String superAdminDashboard(@PathVariable String lang) {
        return "dashboard/superadmin_dash";
    }

    // Admin Dashboard
    @GetMapping("/admin")
    public String adminDashboard(@PathVariable String lang) {
        return "dashboard/admin_dash";
    }

    // Agent Dashboard
    @GetMapping("/agent")
    public String agentDashboard(@PathVariable String lang) {
        return "dashboard/agent_dash";
    }
}
