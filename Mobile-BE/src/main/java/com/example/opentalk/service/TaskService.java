package com.example.opentalk.service;

import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.entity.Project;
import com.example.opentalk.entity.Task;
import com.example.opentalk.repository.ProjectRepository;
import com.example.opentalk.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
//    private final ProjectRepository projectRepository;
//    private final TaskRepository taskRepository;
//    public TaskDTO createTask(TaskDTO taskDTO){
//        Task task = new Task();
//        Project project = projectRepository.getById(taskDTO.getId());
//        task.setProject(project);
//        task.setName(taskDTO.getName());
//        task.setDescription(taskDTO.getDescription());
//        task = taskRepository.save(task);
//        return taskDTO;
//    }
//
//    public TaskDTO mapDataTaskToDTO(Task task){
//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setEmployeeDTO(task.);
//        return taskDTO;
//    }
}
