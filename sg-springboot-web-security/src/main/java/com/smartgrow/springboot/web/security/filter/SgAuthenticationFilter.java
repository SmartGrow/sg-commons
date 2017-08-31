package com.smartgrow.springboot.web.security.filter;

import com.smartgrow.springboot.web.security.authentication.SgAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SgAuthenticationFilter extends OncePerRequestFilter {

    public static final String X_API_AUTH_TOKEN = "X-api-auth-token";
    public static final String X_API_CLIENT_TOKEN = "X-api-client-token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiAuthToken = request.getHeader(X_API_AUTH_TOKEN);
        String apiClientToken = request.getHeader(X_API_CLIENT_TOKEN);

        if (StringUtils.isBlank(apiAuthToken) || StringUtils.isBlank(apiClientToken)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), this.buildMissingAuthHeadersMessage(apiAuthToken, apiClientToken));
        }

        Authentication auth = new SgAuthenticationToken(apiAuthToken, apiClientToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private String buildMissingAuthHeadersMessage(String apiAuthToken, String apiClientToken) {
        String message = "Missing required authentication header(s): ";

        if (StringUtils.isBlank(apiAuthToken)) {
            message += X_API_AUTH_TOKEN;
            if (StringUtils.isBlank(apiClientToken)) {
                message += " and ";
            }
        }
        if (StringUtils.isBlank(apiClientToken)) {
            message += X_API_CLIENT_TOKEN;
        }

        return message;
    }
}
