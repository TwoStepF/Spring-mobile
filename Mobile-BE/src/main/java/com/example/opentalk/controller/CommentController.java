package com.example.opentalk.controller;

import com.example.opentalk.dto.CommentDTO;
import com.example.opentalk.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.createComment(commentDTO));
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentDTO));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<CommentDTO>> getComment(@PathVariable Long taskId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(taskId));
    }

}
