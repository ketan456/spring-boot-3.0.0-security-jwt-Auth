package com.example.springboot3jwtMyproject.Config;


import com.example.springboot3jwtMyproject.Entity.Users;
import com.example.springboot3jwtMyproject.Error.ResourceNotFoundException;
import com.example.springboot3jwtMyproject.Service.CustomUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println(httpServletRequest);
        System.out.println(httpServletRequest.getHeader("Authorization"));

        String authorization = httpServletRequest.getHeader("Authorization");
        String userName = null;
        String token = null;

        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);


            try {
                userName = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                throw new ResourceNotFoundException("Unable to get jwt token");
            } catch (ExpiredJwtException e) {
                throw new ResourceNotFoundException("Session Expire");
            } catch (MalformedJwtException e) {
                throw new ResourceNotFoundException("Invalid Exception");
            }
        } else {
            System.out.println("Jwt does not start with bearer");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            Users userDetails = (Users) userDetailsService.loadUserByUsername(userName);

            if (jwtTokenHelper.validationToken(token, userDetails)) {
                //doing authentications

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                httpServletRequest.setAttribute(Users.LOGIN_USER, userDetails);

            } else {
                System.out.println("Invalid Jwt token");
                throw new ResourceNotFoundException("Session Expire");
            }
        } else {
            System.out.println("User Name is Null and Context is also null");
        }
        filterChain.doFilter(httpServletRequest, response);
    }
}
