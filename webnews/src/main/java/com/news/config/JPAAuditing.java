package com.news.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
public class JPAAuditing  implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		  if (authentication == null || !authentication.isAuthenticated()) {
		   return Optional.of("");
		  }
		  
		  
		  User user = (User) authentication.getPrincipal();
		 
		  return Optional.of(user.getUsername());
	}
	
}
