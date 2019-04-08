package fr.louarn.springSecutityExemple.security.auth;

import fr.louarn.springSecutityExemple.security.service.UserDetailsServiceImpl;
import fr.louarn.springSecutityExemple.security.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String authToken = this.getToken(request);
			if (StringUtils.isNotBlank(authToken)) {
				String username = this.tokenUtil.getUserNameFromToken(authToken);
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				if (this.tokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
							userDetails.getPassword(), userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(token);
				}
			}
		} catch (Exception e) {
			logger.error("Can NOT set user authentication -> Message: {}", e);
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}

		return null;
	}
}
