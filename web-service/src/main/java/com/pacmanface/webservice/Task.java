package com.pacmanface.webservice;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Component
@Entity
public class Task implements Serializable {
    public static final Long SerializationUID = 1L;

    @Id
    private final Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @LastModifiedDate
    private Date lastModDate;

    public Task() {
        lastModDate = new Date();
        id = TaskIdGenerator.generateUniqueIdWithoutJpa();
    }
}
