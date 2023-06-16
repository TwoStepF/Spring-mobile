package com.example.opentalk.controller;

import com.example.opentalk.dto.KeyDTO;
import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping()
    private ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){

        return
    }
}
