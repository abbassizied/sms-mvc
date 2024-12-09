package io.github.abbassizied.sms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.abbassizied.sms.services.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

	// 1 - We enable the password encoder.
    // Define the BCryptPasswordEncoder bean
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	// 2 - We inject our implementation of the users details service.
	@Bean
	UserDetailsServiceImpl customUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	// 3 - We define an authentication provider that references our details service
	@Bean
	AuthenticationProvider customAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;

	}

	// 4 - Finally, we need to reference this auth provider in our configuration.
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.authenticationProvider(customAuthenticationProvider()).build();
	}

}
