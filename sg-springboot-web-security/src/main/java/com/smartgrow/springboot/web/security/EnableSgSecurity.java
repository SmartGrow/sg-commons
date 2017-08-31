package com.smartgrow.springboot.web.security;

import com.smartgrow.springboot.web.security.config.SgWebSecurityConfiguration;
import com.smartgrow.springboot.web.security.filter.SgAuthenticationFilter;
import com.smartgrow.springboot.web.security.provider.SgAuthenticationProvider;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
        SgWebSecurityConfiguration.class,
        SgAuthenticationProvider.class,
        SgAuthenticationFilter.class
})
public @interface EnableSgSecurity {
}
