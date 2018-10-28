package com.github.jactor.rises.commons.time;

import java.time.LocalDateTime;
import java.util.Date;

public class Now {
    private static final Object SYNC = new Object();

    private static volatile Now instance;

    protected LocalDateTime nowAsDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime asDateTime() {
        return instance.nowAsDateTime();
    }

    protected static void reset(Now instance) {
        synchronized (SYNC) {
            Now.instance = instance;
        }
    }

    static {
        reset(new Now());
    }
}
