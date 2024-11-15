package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("/")
	public String dashboard() {
		return "dashboard/dashboard";
	}	
	
	@GetMapping("/superadmin")
	public String superAdminDashboard() {
		return "dashboard/superadmin_dash";
	}
	
	@GetMapping("/admin")
	public String adminDashboard() {
		return "dashboard/admin_dash";
	}
	@GetMapping("/agent")
	public String agentDashboard() {
		return "dashboard/agent_dash";
	}	
	
}
