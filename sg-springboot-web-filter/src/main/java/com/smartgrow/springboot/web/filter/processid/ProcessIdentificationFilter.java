package com.smartgrow.springboot.web.filter.processid;

import com.smartgrow.springboot.core.context.ProcessIdentificationContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class ProcessIdentificationFilter extends GenericFilterBean {

    public static final String PROCESS_ID_FILTER_KEY = "X-process-id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String processId = extractProcessIdFromRequestHeader(request);
            
            ProcessIdentificationContext.set(isNotBlank(processId) ? processId : UUID.randomUUID().toString());
            MDC.put(PROCESS_ID_FILTER_KEY, ProcessIdentificationContext.get());
            
            chain.doFilter(request, response);
        } finally {
            ProcessIdentificationContext.remove();
            MDC.clear();
        }
    }

    private String extractProcessIdFromRequestHeader(ServletRequest request) {
        return ((HttpServletRequest) request).getHeader(PROCESS_ID_FILTER_KEY);
    }
}
