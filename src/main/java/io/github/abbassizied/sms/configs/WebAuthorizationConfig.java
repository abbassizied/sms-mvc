package io.github.abbassizied.sms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebAuthorizationConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
            	.requestMatchers("/", "/product-details/**", "/login", "/register", "/contact", "/about").permitAll() 
            	// Allow authenticated users
            	.requestMatchers("/dashboard").authenticated() // Ensure this is correct ??????????
            	// Securing password management with specific authorities
                .requestMatchers("/users/update-password").authenticated()  // Ensure this is correct ??????????
                // Allow access to change password for authenticated users
                .requestMatchers("/profile/change-password").authenticated() 
                
                // Securing role-related endpoints with specific authorities
                .requestMatchers( "/roles", "/roles/").hasAuthority("ROLE_READ")    // View roles
                .requestMatchers("/roles/list").hasAuthority("ROLE_READ")           // View roles
                .requestMatchers("/roles/show/**").hasAuthority("ROLE_READ")        // View Role Details
                .requestMatchers("/roles/add").hasAuthority("ROLE_WRITE")           // Create roles
                .requestMatchers("/roles/edit/**").hasAuthority("ROLE_UPDATE")      // Update role form
                .requestMatchers("/roles/update/**").hasAuthority("ROLE_UPDATE")    // Submit update form
                .requestMatchers("/roles/delete/**").hasAuthority("ROLE_DELETE")    // Delete roles

                // Securing user-related endpoints with specific authorities
                .requestMatchers("/users", "/users/").hasAuthority("USER_READ")    // View users
                .requestMatchers("/users/list").hasAuthority("USER_READ")          // View users
                .requestMatchers("/users/add").hasAuthority("USER_WRITE")          // Create users
                .requestMatchers("/users/update").hasAuthority("USER_UPDATE")      // Update users
                .requestMatchers("/users/delete").hasAuthority("USER_DELETE")      // Delete users
                
                
            	.anyRequest().authenticated() // All other pages require authentication 
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Specify your custom login page URL
                .permitAll() // Allow access to the login page to everyone
                .defaultSuccessUrl("/", true) // Redirect to a default page on successful login
                .failureUrl("/login?error=true") // Handle login failure
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Route for logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
    	return (web) -> web.ignoring().requestMatchers("/backoffice/**" , "/frontoffice/**" , "/shared/**", "/uploads/**");
    }
}
