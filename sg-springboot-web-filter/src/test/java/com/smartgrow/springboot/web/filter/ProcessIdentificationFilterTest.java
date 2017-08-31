package com.smartgrow.springboot.web.filter;

import com.smartgrow.springboot.core.context.ProcessIdentificationContext;
import com.smartgrow.springboot.web.filter.processid.ProcessIdentificationFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.MDC;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static com.smartgrow.springboot.web.filter.processid.ProcessIdentificationFilter.PROCESS_ID_FILTER_KEY;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessIdentificationFilterTest {

    private ProcessIdentificationFilter processIdentificationFilter;

    @Before
    public void setUp() {
        processIdentificationFilter = new ProcessIdentificationFilter();
    }

    @Test
    public void doFilter() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        // Given I have the processId on the request
        String processId = UUID.randomUUID().toString();
        when(request.getHeader(eq(PROCESS_ID_FILTER_KEY))).thenReturn(processId);

        // And the Filter get my request
        processIdentificationFilter.doFilter(request, response, chain);

        // I expect the filter to get the process Id header from request
        verify(request, only()).getHeader(eq(PROCESS_ID_FILTER_KEY));

        // I expect the filter to continue chain calling 'doFilter' on filterChain
        verify(chain, only()).doFilter(eq(request), eq(response));

        // I expect the MDC and ProcessIdentificationContext to be clean after I get my response
        assertNull(processId, MDC.get(PROCESS_ID_FILTER_KEY));
        assertNull(processId, ProcessIdentificationContext.get());
    }
}