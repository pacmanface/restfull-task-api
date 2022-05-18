package com.pacmanface.webservice;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

@Component
public final class TaskIdGenerator {
    private static AtomicLong counter = new AtomicLong();

    public static Long generateUniqueIdWithoutJpa() {
        return counter.incrementAndGet();
    }
}
