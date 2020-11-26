package com.abraham.app.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.abraham.app.security.service.EditorDetailsService;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	EditorDetailsService editorDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			if (token != null && jwtProvider.validateToken(token)) {

				String editorName = jwtProvider.getNombreUsuarioFromToken(token);

				UserDetails editorDetails = editorDetailsService.loadUserByUsername(editorName);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(editorDetails, null,
						editorDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			logger.error("Fail in method doFilter" + e.getMessage());

		}

		filterChain.doFilter(request, response);

	}

	private String getToken(HttpServletRequest request) {

		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer")) {

			return header.replace("Bearer", "");
		}

		return null;
	}

}
