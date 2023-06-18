package com.example.opentalk.service;

import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.entity.*;
import com.example.opentalk.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final StatusRepository statusRepository;
    private final UserProjectRepository userProjectRepository;

    public TaskDTO createTask(TaskDTO taskDTO){
        Task task = new Task();
        Project project = projectRepository.getById(taskDTO.getProjectId());
        task.setProject(project);
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        Status status = statusRepository.findStatusByName("todo").orElse(null);
        Employee employee = employeeRepository.getById(taskDTO.getEmployeeId());
        UserProject userProjects = userProjectRepository.getUserProjectsByProjectAndEmployee(project, employee);
        if(userProjects == null){
            throw new RuntimeException();
        }
        task.setEmployee(employee);
        task.setStatus(status);
        task = taskRepository.save(task);
        return mapDataTaskToDTO(task);
    }

    public TaskDTO mapDataTaskToDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(task.getDescription());
        taskDTO.setName(task.getName());
        taskDTO.setEmployeeName(task.getEmployee().getName());
        taskDTO.setEmployeeId(task.getEmployee().getId());
        taskDTO.setProjectName(task.getProject().getName());
        taskDTO.setProjectId(task.getProject().getId());
        taskDTO.setStatus(task.getStatus().getName());
        taskDTO.setId(task.getId());
        return taskDTO;
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        Task task = taskRepository.getById(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
//        Status status = statusRepository.findStatusByName(taskDTO.getStatus()).orElse(null);
        Employee employee = employeeRepository.getById(taskDTO.getEmployeeId());
        Project project = projectRepository.getById(task.getProject().getId());
        UserProject userProjects = userProjectRepository.getUserProjectsByProjectAndEmployee(project, employee);
        if(userProjects == null){
            throw new RuntimeException();
        }
        task.setEmployee(employee);
        task = taskRepository.save(task);
        return mapDataTaskToDTO(task);
    }

    public TaskDTO changeStatusTask(TaskDTO taskDTO) {
        Task task = taskRepository.getById(taskDTO.getId());
        Status status = statusRepository.findStatusByName(taskDTO.getStatus()).orElse(null);
        task.setStatus(status);
        return mapDataTaskToDTO(task);
    }

    public List<TaskDTO> getListTaskByProjectId(long projectId) {
        return taskRepository.getByProjectId(projectId).stream().map(this::mapDataTaskToDTO).collect(Collectors.toList());
    }
}
