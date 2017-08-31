package com.smartgrow.springboot.core.context;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ProcessIdentificationContextTest {

    @Test
    public void test() {

        // When nothing set, I expect to get null
        assertNull(ProcessIdentificationContext.get());

        String processId = UUID.randomUUID().toString();

        // When I set the processId, I expect to get the same value
        ProcessIdentificationContext.set(processId);
        assertEquals(processId, ProcessIdentificationContext.get());

        // When I remove the processId, I expect to get null
        ProcessIdentificationContext.remove();
        assertNull(ProcessIdentificationContext.get());
    }

}