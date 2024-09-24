package org.drobysh.org.store.repositories;

import org.drobysh.org.store.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
    Optional<ProjectEntity> findByName(String name);
    Stream<ProjectEntity> streamAll();
    Stream<ProjectEntity> streamAllByNameStartsWithIgnoreCase(String prefix);
}
