package com.exo1.exo1.service;

import com.exo1.exo1.Utils;
import com.exo1.exo1.dto.TaskDto;
import com.exo1.exo1.entity.Task;
import com.exo1.exo1.mapper.TaskMapper;
import com.exo1.exo1.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private Utils utils;

    public List<TaskDto> findAll(Pageable pageable) {
        return taskMapper.toDtos(taskRepository.findAll(pageable).getContent());
    }

    public TaskDto findById(long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }

    public TaskDto save(TaskDto taskDto) {
        TaskDto savedTask = taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
        utils.refreshMaterializedView();
        return savedTask;
    }

    public TaskDto update(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        taskDto.setId(existingTask.getId());

        TaskDto savedTask = taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
        utils.refreshMaterializedView();

        return savedTask;
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
        utils.refreshMaterializedView();
    }


}
