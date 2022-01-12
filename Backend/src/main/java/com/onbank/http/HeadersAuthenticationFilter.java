package com.onbank.http;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@Component
public class HeadersAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String userID = request.getHeader("userID");
        if (!isValid(userID)) {
            throw new SecurityException();
        }
        Long id = Long.valueOf(userID);

        Authentication auth = new AuthenticationToken(id);
        SecurityContextHolder.getContext().setAuthentication(auth);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        filterChain.doFilter(request, response);
    }
}
