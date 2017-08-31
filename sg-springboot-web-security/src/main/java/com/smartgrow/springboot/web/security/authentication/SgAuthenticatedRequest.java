package com.smartgrow.springboot.web.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SgAuthenticatedRequest extends SgAuthenticationToken {


    public SgAuthenticatedRequest(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public SgAuthenticatedRequest(String apiAuthToken, String apiClientToken) {
        super(apiAuthToken, apiClientToken);
    }

    public SgAuthenticatedRequest(Collection<? extends GrantedAuthority> authorities, String apiAuthToken, String apiClientToken) {
        super(authorities, apiAuthToken, apiClientToken);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
