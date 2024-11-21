package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping()
    public String dashboard(Model model) {
        // Return the dashboard view
        return "dashboard/dashboard";
    }

    // Super Admin Dashboard
    @GetMapping("/superadmin")
    public String superAdminDashboard() {
        return "dashboard/superadmin_dash";
    }

    // Admin Dashboard
    @GetMapping("/admin")
    public String adminDashboard() {
        return "dashboard/admin_dash";
    }

    // Agent Dashboard
    @GetMapping("/agent")
    public String agentDashboard() {
        return "dashboard/agent_dash";
    }
}
