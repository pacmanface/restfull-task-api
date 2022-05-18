package com.pacmanface.webservice.hateoas;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import java.util.Date;

@Data
@NoArgsConstructor
public class TaskModel extends RepresentationModel<TaskModel> {
    private Long id;
    private String name;
    private String description;
    private Date lastModDate;
}
