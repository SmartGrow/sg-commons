package com.smartgrow.springboot.web.security.provider;

import com.smartgrow.springboot.web.security.authentication.SgAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SgAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {
        SgAuthenticationToken token = (SgAuthenticationToken) authentication;

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SgAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
