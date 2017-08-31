package com.smartgrow.springboot.web.security.authentication;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SgAuthenticationToken extends AbstractAuthenticationToken {

    @Getter
    private final String apiAuthToken;

    @Getter
    private final String apiClientToken;

    public SgAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        this(authorities, null, null);
    }

    public SgAuthenticationToken(String apiAuthToken, String apiClientToken) {
        this(null, apiAuthToken, apiClientToken);
    }

    public SgAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String apiAuthToken, String apiClientToken) {
        super(authorities);
        this.apiAuthToken = apiAuthToken;
        this.apiClientToken = apiClientToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SgAuthenticationToken that = (SgAuthenticationToken) o;

        if (apiAuthToken != null ? !apiAuthToken.equals(that.apiAuthToken) : that.apiAuthToken != null) return false;
        return apiClientToken != null ? apiClientToken.equals(that.apiClientToken) : that.apiClientToken == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (apiAuthToken != null ? apiAuthToken.hashCode() : 0);
        result = 31 * result + (apiClientToken != null ? apiClientToken.hashCode() : 0);
        return result;
    }
}
