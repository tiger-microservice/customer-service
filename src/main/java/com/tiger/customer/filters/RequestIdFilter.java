package com.tiger.customer.filters;

import com.tiger.customer.constants.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Generate a unique request ID
        String requestId = request.getHeader(AppConstants.APP_REQUEST_ID);
        String requestIdService = UUID.randomUUID().toString();

        if (requestId == null) {
            // Add the request ID to the response header
            requestId = requestIdService;
        } else {
            requestId = requestId + " " + requestIdService;
        }

        MDC.put(AppConstants.APP_REQUEST_ID, requestIdService);

        response.addHeader(AppConstants.APP_REQUEST_ID, requestId);

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
