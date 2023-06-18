package com.example.opentalk.controller;

import com.example.opentalk.dto.*;
import com.example.opentalk.entity.UserProject;
import com.example.opentalk.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResponseEntity<?> AddEmployeeCollab(@RequestBody List<EmployeeDTO> employeeDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(projectService.addUserToProject(employeeDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("false");
        }
    }

    @GetMapping("/employee-project/")
    public ResponseEntity<List<EmployeeDTO>> GetEmployeeProject(@PathParam("projectId") long projectId){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getEmployeeProject(projectId));
    }
}
