package com.smartgrow.springboot.web.filter;

import com.smartgrow.springboot.web.filter.processid.ProcessIdentificationFilter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
        ProcessIdentificationFilter.class
})
public @interface EnableSgFilters {
}
