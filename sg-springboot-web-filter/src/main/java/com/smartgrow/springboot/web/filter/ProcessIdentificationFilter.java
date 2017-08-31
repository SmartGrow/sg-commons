package com.smartgrow.springboot.web.filter;

import com.smartgrow.springboot.core.context.ProcessIdentificationContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class ProcessIdentificationFilter implements Filter, NamedFilter {

    protected static final String PROCESS_ID_FILTER_KEY = "X-process-id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String processId = extractProcessIdFromRequestHeader(request);
            ProcessIdentificationContext.set(isNotBlank(processId) ? processId : UUID.randomUUID().toString());
            MDC.put(PROCESS_ID_FILTER_KEY, ProcessIdentificationContext.get());
        } finally {
            chain.doFilter(request, response);
            ProcessIdentificationContext.remove();
            MDC.clear();
        }
    }

    private String extractProcessIdFromRequestHeader(ServletRequest request) {
        return ((HttpServletRequest) request).getHeader(PROCESS_ID_FILTER_KEY);
    }

    @Override
    public String getName() {
        return PROCESS_ID_FILTER_KEY;
    }
}
