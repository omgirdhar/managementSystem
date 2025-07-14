package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()).authenticationProvider(authenticationProvider())
		.authorizeHttpRequests((authorize) -> 
        authorize.requestMatchers("/showLogin","/css/**", "/js/**","/videos/**","/").permitAll()
        .requestMatchers("/dashboard").hasAnyAuthority("ADMIN","USER","SUPERADMIN")
        .anyRequest().authenticated())
		.formLogin((form) -> form.loginPage("/showLogin").loginProcessingUrl("/login")
		.defaultSuccessUrl("/dashboard",true).permitAll())
		.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/showLogin").permitAll());

        return http.build();
    }

    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(this.getCustomUser());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
    
    @Bean
	public CustomUserDetailsService getCustomUser() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
