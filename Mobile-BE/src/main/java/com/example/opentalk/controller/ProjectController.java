package com.example.opentalk.controller;

import com.example.opentalk.dto.LoginRequest;
import com.example.opentalk.dto.ProjectDTO;
import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.dto.UserProjectDTO;
import com.example.opentalk.entity.UserProject;
import com.example.opentalk.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> GetProject() throws Throwable {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProject());
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDTO> CreateProject(@RequestBody ProjectDTO projectDTO) throws Throwable {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.addProject(projectDTO));
    }

    @PostMapping("/add-employee-collab")
    public ResponseEntity<?> AddEmployeeCollab(@RequestBody UserProjectDTO userProjectDTO) throws Throwable {
        projectService.addUserToProject(userProjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("oke");
    }
}
