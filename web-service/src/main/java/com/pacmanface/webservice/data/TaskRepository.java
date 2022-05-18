package com.pacmanface.webservice.data;

import com.pacmanface.webservice.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
