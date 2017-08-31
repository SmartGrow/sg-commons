package com.smartgrow.springboot.core.context;

public final class ProcessIdentificationContext {

    private static final ThreadLocal<String> PROCESS_IDENTIFICATION_CONTEXT = new ThreadLocal<>();

    private ProcessIdentificationContext() {}

    public static String get() {
        return PROCESS_IDENTIFICATION_CONTEXT.get();
    }

    public static void set(String processId) {
        PROCESS_IDENTIFICATION_CONTEXT.set(processId);
    }

    public static void remove() {
        PROCESS_IDENTIFICATION_CONTEXT.remove();
    }
}
