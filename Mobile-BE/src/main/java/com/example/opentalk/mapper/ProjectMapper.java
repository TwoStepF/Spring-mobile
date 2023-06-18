package com.example.opentalk.mapper;

import com.example.opentalk.dto.ProjectDTO;
import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.Project;
import com.example.opentalk.entity.Status;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.repository.StatusRepository;
import com.example.opentalk.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectMapper{

    private final AuthService authService;
    private final StatusRepository statusRepository;
    private final EmployeeMapper employeeMapper;

    public Project toEntity(ProjectDTO projectdto){
        return Project.builder()
                .creator(authService.getCurrentUser())
                .name(projectdto.getName())
                .image(projectdto.getImg())
                .build();
    }

    public ProjectDTO toDto(Project project){
        return ProjectDTO.builder()
                .id(project.getId())
                .creator(employeeMapper.toDto(project.getCreator()))
                .name(project.getName())
                .img(project.getImage())
                .build();
    }

}
