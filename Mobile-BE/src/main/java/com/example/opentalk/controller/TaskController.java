package com.example.opentalk.controller;

import com.example.opentalk.dto.KeyDTO;
import com.example.opentalk.dto.TaskDTO;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> CreateTask(@RequestBody TaskDTO taskDTO) throws Throwable {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.createTask(taskDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<?> UpdateTask(@RequestBody TaskDTO taskDTO) throws Throwable {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(taskDTO));
    }
}
