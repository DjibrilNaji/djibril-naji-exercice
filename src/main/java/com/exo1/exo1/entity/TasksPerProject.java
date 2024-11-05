package com.exo1.exo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks_per_project")
public class TasksPerProject {
    @Id
    @Column(name = "projet_id")
    private Long projetId;

    @Column(name = "projet_name")
    private String projetName;

    @Column(name = "task_count")
    private Long taskCount;
}