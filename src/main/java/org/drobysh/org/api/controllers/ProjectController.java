package org.drobysh.org.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.drobysh.org.api.dto.ProjectDto;
import org.drobysh.org.api.exceptions.BadRequestException;
import org.drobysh.org.api.exceptions.NotFoundException;
import org.drobysh.org.api.factrories.ProjectDtoFactory;
import org.drobysh.org.store.entities.ProjectEntity;
import org.drobysh.org.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Locale.filter;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("api/projects")
public class ProjectController {
    private final ProjectDtoFactory projectDtoFactory;
    private final ProjectRepository projectRepository;
    @PostMapping("/")
    public ProjectDto createProject(@RequestParam String name) {
        if(name.trim().isEmpty()){
            throw new BadRequestException("Name can't be Empty");
        }
                projectRepository.findByName(name).ifPresent(project-> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists.", name));});

                ProjectEntity project = projectRepository.saveAndFlush(
                    ProjectEntity.builder()
                        .name(name)
                            .build()
        );
        //TODO:insert some
        return projectDtoFactory.makeProjectDto(project);
    }
    @PatchMapping("/{project_id}")
    public ProjectDto editProject(@PathVariable Long id,@RequestParam String name) {

        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException(
                                String.format(
                                        "Project with \"%s\" doesn't exist.",id
                                )
                        )
                );

        projectRepository.findByName(name)
        .filter(anotherProject ->!Objects.equals(anotherProject.getId(),id))
                .ifPresent(anotherProject-> {
            throw new BadRequestException(String.format("Project \"%s\" already exists.", name));});

        project.setName(name);
        project = projectRepository.saveAndFlush(project);

        return projectDtoFactory.makeProjectDto(project);
    }
    @GetMapping("/")
    public List<ProjectDto> fetchProject(@RequestParam (value = "prefix_name",required = false)
                                             Optional<String> stringOptional){
        stringOptional=stringOptional.filter(p->!p.trim().isEmpty());

        Stream<ProjectEntity> projectStream = stringOptional.map(projectRepository::streamAllByNameStartsWithIgnoreCase)
                .orElseGet(projectRepository::streamAll);

        return projectStream
                .map(projectDtoFactory::makeProjectDto)
                .collect(Collectors.toList());
    }
    @DeleteMapping("/{project_id}")
    public Boolean deleteProject(@PathVariable Long id) {
        projectRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format(
                                "Project with \"%s\" doesn't exists.",id
                        )
                ));
        projectRepository.deleteById(id);
        return true;
    }

}
