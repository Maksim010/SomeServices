package org.drobysh.org.api.factrories;

import org.drobysh.org.api.dto.ProjectDto;
import org.drobysh.org.api.dto.TaskDto;
import org.drobysh.org.store.entities.ProjectEntity;
import org.drobysh.org.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {


    public TaskDto makeProjectDto(TaskEntity entity) {
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .description(entity.getDescription())
                .build();
    }

}
