package com.pacmanface.webservice;

import com.pacmanface.webservice.data.TaskStorage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskConfigTest {

    @Autowired
    TaskStorage storage;

    @Test
    public void inMemoryStorageInitializationTest() {
        Assert.assertFalse(storage.getTaskStorage().isEmpty());
        Assert.assertTrue(storage.getTaskStorage().size() == 20);
    }
}
