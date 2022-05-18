package com.pacmanface.webservice.hateoas;

import com.pacmanface.webservice.Task;
import com.pacmanface.webservice.web.InMemoryTaskController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskModel> {

    public TaskModelAssembler() {
        super(InMemoryTaskController.class, TaskModel.class);
    }

    @Override
    public TaskModel toModel(Task task) {
        TaskModel model = instantiateModel(task);

        model.add(linkTo(
                methodOn(InMemoryTaskController.class)
                        .getById(task.getId()))
                .withSelfRel());

        model.setId(task.getId());
        model.setName(task.getName());
        model.setDescription(task.getDescription());
        model.setLastModDate(task.getLastModDate());

        return model;
    }

    @Override
    public CollectionModel<TaskModel> toCollectionModel(Iterable<? extends Task> entities) {
        CollectionModel<TaskModel> models = super.toCollectionModel(entities);

        models.add(linkTo(
                methodOn(InMemoryTaskController.class)
                        .getTasks())
                .withSelfRel());

        return models;
    }
}
