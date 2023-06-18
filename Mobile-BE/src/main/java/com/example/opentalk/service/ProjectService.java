package com.example.opentalk.service;

import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.dto.ProjectDTO;
import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.dto.UserProjectDTO;
import com.example.opentalk.entity.*;
import com.example.opentalk.mapper.ProjectMapper;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectMapper projectMapper;
    private final StatusRepository statusRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final UserProjectRepository userProjectRepository;
    private final TaskRepository taskRepository;

    public ProjectDTO addProject(ProjectDTO projectDTO) throws Throwable {
        Status status = statusRepository.findStatusByName("Processing").orElseThrow(() -> new StatusError(HttpStatus.BAD_REQUEST, "can't find status name"));
        projectDTO.setStatus(status);
        Project project = projectMapper.toEntity(projectDTO);

        projectRepository.save(project);
        UserProject userProject = new UserProject();
        userProject.setProject(project);
        userProject.setEmployee(project.getCreator());
        userProjectRepository.save(userProject);
        return projectMapper.toDto(projectRepository.save(project));
    }

    public List<EmployeeDTO> addUserToProject(List<EmployeeDTO> employeeDTO){
        List<UserProject> userProjects = new ArrayList<>();
        employeeDTO.stream().forEach(e -> {
            Employee employee = employeeRepository.findEmployeeByEmail(e.getEmail());
            Project project = projectRepository.getById(e.getProjectId());
            UserProject userProject = new UserProject();
            userProject.setProject(project);
            userProject.setEmployee(employee);
            userProjects.add(userProject);
        });
        return userProjectRepository.saveAll(userProjects).stream().map(userProject -> {
            return MapDataEmployeetoDTO(userProject.getEmployee());
        }).collect(Collectors.toList());
    }

    public List<ProjectDTO> getAllProject() {
        List<Project> project = projectRepository.findAll();
        return project.stream().map(this::mapdataProjecttodto).collect(Collectors.toList());
    }

    public ProjectDTO mapdataProjecttodto(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setStatus(project.getStatus());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(project.getCreator().getName());
        employeeDTO.setId(project.getId());
        employeeDTO.setRole(project.getCreator().getRole());
        projectDTO.setCreator(employeeDTO);
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        return projectDTO;
    }

    public List<EmployeeDTO> getEmployeeProject(long projectId) {
        Project project = projectRepository.getById(projectId);
        return userProjectRepository.getUserProjectsByProject(project).stream().map(userProject -> {
            return MapDataEmployeetoDTO(userProject.getEmployee());
        }).collect(Collectors.toList());
    }

    public EmployeeDTO MapDataEmployeetoDTO(Employee employee) {
        return EmployeeDTO.builder()
                .name(employee.getName())
                .role(employee.getRole())
                .email(employee.getEmail())
                .id(employee.getId())
                .build();
    }
}
