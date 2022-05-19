package com.pacmanface.webservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskIdGeneratorTest {

    static List<Long> longList = new CopyOnWriteArrayList<>();
    static int threads = 7;
    static int runs = 10;
    static int threadsOnRuns = (int) threads * runs;

    @Test
    public void doesGeneratorGenerateUniqueIds() {
        GeneratorRunnable runnable = new GeneratorRunnable();
        for (int i = 0; i < threads; i++) {
            new Thread(runnable).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertFalse(longList.isEmpty());
        Assert.assertTrue(longList.size()== threadsOnRuns);
        Assert.assertEquals(longList.get(longList.size()-1),new Long(threadsOnRuns));
    }

    class GeneratorRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < runs; i++) {
                longList.add(TaskIdGenerator.generateUniqueIdWithoutJpa());
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
