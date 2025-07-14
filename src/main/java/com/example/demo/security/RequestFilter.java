package com.example.demo.security;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class RequestFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                try {
                    HttpSession session = request.getSession();
                     updateCorsHeaders(response);
                     if(session != null) {
                         filterChain.doFilter(request, response);
                     }else 
                         filterChain.doFilter(request, response);
                     
                }catch (Exception e) {
                    System.out.println("Custom Filter Error");
                    e.printStackTrace();
                }
        
    }

    @Override
	protected boolean shouldNotFilterAsyncDispatch() {
		return true;
	}

	private void updateCorsHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
	}
    
}
