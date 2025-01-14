package org.drobysh.org.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_state")
public class TaskStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;

    private Long ordinal;
    @Builder.Default
    private Instant createdAt = Instant.now() ;
    @OneToMany
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "task_state_id",referencedColumnName = "id")
    private List<TaskEntity> tasks=new ArrayList<>();


}
