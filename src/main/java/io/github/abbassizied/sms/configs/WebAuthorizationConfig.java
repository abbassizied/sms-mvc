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
            	.requestMatchers("/login", "/register","/","/about","contact").permitAll() 
            	.requestMatchers("/dashboard/**").authenticated() // Allow authenticated users
            	.requestMatchers("/users/**", "/roles/**").hasAnyAuthority("ROLE_SUPERADMIN")
                .requestMatchers("/users/update-password").authenticated() // Ensure this is correct ??????????
                .requestMatchers("/profile/change-password").authenticated() // Allow access to change password ?????????? 
            	.anyRequest().authenticated() // All other pages require authentication 
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Specify your custom login page URL
                .permitAll() // Allow access to the login page to everyone
                .defaultSuccessUrl("/home", true) // Redirect to a default page on successful login
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
        return (web) -> web.ignoring().requestMatchers("/**");
    }

}