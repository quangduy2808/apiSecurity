package com.news.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSucsseHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String URLTarget="";
		List<String> roles = new ArrayList<String>();
		Collection<GrantedAuthority> authorities =  (Collection<GrantedAuthority>) authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			roles.add(grantedAuthority.getAuthority());
		}
		if(isAdmin(roles)) {
			URLTarget = "/admin";
		}else if(isUser(roles)) {
			URLTarget = "/user";
		}
		redirectStrategy.sendRedirect(request, response, URLTarget);
	}
	private boolean isAdmin(List<String> roles) {
		for (String role : roles) {
			if(role.contains("ADMIN")) return true;
		}
		return false;
	}
	private boolean isUser(List<String> roles) {
		for (String role : roles) {
			if(role.contains("USER")) return true;
		}
		return false;
	}
	

}
