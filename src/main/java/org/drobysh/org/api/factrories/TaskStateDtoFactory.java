package org.drobysh.org.api.factrories;

import org.drobysh.org.api.dto.ProjectDto;
import org.drobysh.org.api.dto.TaskStateDto;
import org.drobysh.org.store.entities.ProjectEntity;
import org.drobysh.org.store.entities.TaskStateEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskStateDtoFactory {


    public TaskStateDto makeProjectDto(TaskStateEntity entity) {
        return TaskStateDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .ordinal(entity.getOrdinal())
                .build();
    }

}
