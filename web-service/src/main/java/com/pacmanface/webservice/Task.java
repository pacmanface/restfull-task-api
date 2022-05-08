package com.pacmanface.webservice;

import lombok.Data;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Component
@Entity
public class Task {
    private String name;
    private String description;
    private final UUID uuid;
    private Date lastModDate;
    @Id
    private Long id;

    public Task() {
        lastModDate = new Date();
        id = UniqueIdGenerator.counter.incrementAndGet();
        uuid = UUID.randomUUID();
    }
}
