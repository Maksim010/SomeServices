package org.drobysh.org.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;
    @Builder.Default
    private Instant createdAt = Instant.now() ;
    @OneToMany
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private List<TaskStateEntity> taskStates=new ArrayList<>();


}
