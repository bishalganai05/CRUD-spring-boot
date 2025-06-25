package com.bishal.gms.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bishal.gms.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
			.httpBasic(withDefaults());
		return http.build();
	}
    
    @Bean
    public UserDetailsService userDetailsService() {
    	return new CustomUserDetailsService();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @SuppressWarnings("deprecation")
    @Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    	
    	return new ProviderManager(daoAuthenticationProvider);
    }
}
