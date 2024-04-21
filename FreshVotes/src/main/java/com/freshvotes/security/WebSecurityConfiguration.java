package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
	{
		http
        .authorizeHttpRequests((authz) -> authz
        	.requestMatchers("/").permitAll()
        	.requestMatchers("/login").permitAll()
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/user").hasRole("USER")
            .anyRequest().authenticated()
        );
    return http.build();
		
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() 
	{
		UserDetails admin = User.builder()
				.username("testUserOne")
				.password("password1")
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.builder()
				.username("testUserTwo")
				.password("password2")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
}
