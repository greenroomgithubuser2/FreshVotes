package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user1 = User.withUsername("admin")
				 .password(passwordEncoder.encode("admin123")) 
				 .roles("ADMIN") 
				 .build();
		
		UserDetails user2 = User.withUsername("user")
				 .password (passwordEncoder.encode("user123")) 
				 .roles("USER") 
				 .build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Bean
	public PasswordEncoder encoder() { 
		return new BCryptPasswordEncoder(); 
	}
	
	public SecurityFilterChain configure(HttpSecurity http)throws Exception {
		return http .authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/customlogin").hasAnyRole("ADMIN", "USER");
            auth.requestMatchers("/dashboard").hasRole("USER");
            auth.requestMatchers("/library/books").hasRole("ADMIN");
        })
                .formLogin(withDefaults())
                .build();
	}
}
