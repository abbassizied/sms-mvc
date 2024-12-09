package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.forms.OnCreate;
import io.github.abbassizied.sms.forms.OnUpdate;
import io.github.abbassizied.sms.forms.RoleForm;
import io.github.abbassizied.sms.entities.Privilege;
import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.repositories.PrivilegeRepository;
import io.github.abbassizied.sms.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private PrivilegeRepository privilegeRepository;

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService; 
	}

	@GetMapping 
	public String index() {
		return "redirect:/roles/list";
	}

	@GetMapping("/list")
	public String listRoles(Model model) {
/*	
        // Get the current authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Print the authorities of the currently authenticated user
        System.out.println("Authenticated User: " + authentication.getName());  // User's username
        System.out.println("Authorities (Roles):");
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println("Authority: " + authority.getAuthority());  // Print each authority (role)
        }		
*/
		List<Role> roles = new ArrayList<Role>(roleService.getAllRoles());

		if (roles.size() == 0)
			roles = null;

		model.addAttribute("roles", roles);
		return "role/listRoles";
	}

	@GetMapping("/add")
	public String showAddRoleForm(Model model) {
		RoleForm roleForm = new RoleForm();
		model.addAttribute("roleForm", roleForm);
		model.addAttribute("privileges", privilegeRepository.findAll()); // Fetch from your database or service
		return "role/addRole";
	}

	@PostMapping("add")
	public String addRole( @ModelAttribute @Validated(OnCreate.class) RoleForm roleForm, 
			               BindingResult bindingResult, 
			               Model model) {

		if (bindingResult.hasErrors()) { 
			model.addAttribute("privileges", privilegeRepository.findAll()); // Re-fetch privileges for the form
			return "role/addRole"; // Return to form with error messages
		}

		Role role = new Role();
		role.setName(roleForm.getName());
		role.setPrivileges(roleForm.getPrivileges());
		
		// Save role with its privileges
		this.roleService.saveRole(role);
		return "redirect:/roles/list";
	}

	@GetMapping("/show/{id}")
	public String showRole(@PathVariable final Long id, final Model model) {
		Role role = roleService.getRoleById(id);
		model.addAttribute("role", role); 
		return "role/showRole";
	}	
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm( @PathVariable final Long id, Model model) { 
		
		Role role = roleService.getRoleById(id);
		
		RoleForm roleForm = new RoleForm();
		roleForm.setId(role.getId());
		roleForm.setName(role.getName());
		roleForm.setPrivileges(role.getPrivileges());
		
		model.addAttribute("roleForm", roleForm);
		model.addAttribute("privileges", privilegeRepository.findAll()); // Fetch from your database or service
		
		return "role/updateRole";
	}

	@PostMapping("/update")
	public String updateUser( @ModelAttribute @Validated(OnUpdate.class) RoleForm roleForm, 
			                  BindingResult bindingResult, 
			                  Model model, 
			                  @RequestParam Set<Integer> privileges) {
		
	    if (bindingResult.hasErrors()) {
			model.addAttribute("privileges", privilegeRepository.findAll()); // Re-fetch privileges for the form
	        return "role/updateRole"; // If validation fails, return the form view
	    }		
         
	    // Fetch the existing role and update it
	    Role existingRole = roleService.getRoleById(roleForm.getId());
	    existingRole.setName(roleForm.getName());

	    // Update privileges
	    Set<Privilege> selectedPrivileges = privileges.stream()
	            .map(privilegeId -> privilegeRepository.findById(privilegeId)
	                    .orElseThrow(() -> new IllegalArgumentException("Privilege not found: " + privilegeId)))
	            .collect(Collectors.toSet());

	    existingRole.setPrivileges(selectedPrivileges);
	    
	    // Save the updated role
	    this.roleService.saveRole(existingRole); // Save the updated role	 
		return "redirect:/roles/list"; // Redirect to roles list
	}

	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable Long id, Model model) {
		roleService.deleteRole(id);
		return "redirect:../list";
	}

}
