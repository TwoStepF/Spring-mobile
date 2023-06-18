package com.example.opentalk.service;

import com.example.opentalk.dto.CommentDTO;
import com.example.opentalk.entity.Comment;
import com.example.opentalk.entity.Task;
import com.example.opentalk.repository.CommentRepository;
import com.example.opentalk.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private TaskRepository taskRepository;
    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        Task task = taskRepository.getById(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setTask(task);
        commentRepository.save(comment);
        return commentDTO;
    }
}
