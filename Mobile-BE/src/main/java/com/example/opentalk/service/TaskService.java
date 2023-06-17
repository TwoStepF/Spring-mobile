package com.example.opentalk.service;

import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.Project;
import com.example.opentalk.entity.Status;
import com.example.opentalk.entity.Task;
import com.example.opentalk.repository.EmployeeRepository;
import com.example.opentalk.repository.ProjectRepository;
import com.example.opentalk.repository.StatusRepository;
import com.example.opentalk.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final StatusRepository statusRepository;
    public TaskDTO createTask(TaskDTO taskDTO){
        Task task = new Task();
        Project project = projectRepository.getById(taskDTO.getProjectId());
        task.setProject(project);
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        Status status = statusRepository.findStatusByName("Processing").orElse(null);
        Employee employee = employeeRepository.getById(taskDTO.getEmployeeId());
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
        Status status = statusRepository.findStatusByName(taskDTO.getStatus()).orElse(null);
        Employee employee = employeeRepository.getById(taskDTO.getEmployeeId());
        task.setEmployee(employee);
        task.setStatus(status);
        task = taskRepository.save(task);
        return mapDataTaskToDTO(task);
    }
}
