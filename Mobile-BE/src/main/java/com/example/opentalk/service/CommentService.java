package com.example.opentalk.service;

import com.example.opentalk.dto.CommentDTO;
import com.example.opentalk.entity.Comment;
import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.Task;
import com.example.opentalk.entity.UserProject;
import com.example.opentalk.repository.CommentRepository;
import com.example.opentalk.repository.EmployeeRepository;
import com.example.opentalk.repository.TaskRepository;
import com.example.opentalk.repository.UserProjectRepository;
import com.querydsl.core.types.ConstantImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private TaskRepository taskRepository;
    private EmployeeRepository employeeRepository;
    private UserProjectRepository userProjectRepository;
    private AuthService authService;

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        Employee employee = authService.getCurrentUser();
        Task task = taskRepository.getById(commentDTO.getId());
        UserProject userProject = userProjectRepository.getUserProjectsByProjectAndEmployee(task.getProject(), employee);
        if(userProject == null){
            throw new RuntimeException();
        }
        comment.setContent(commentDTO.getContent());
        comment.setTask(task);
        comment.setEmployee(employee);
        commentRepository.save(comment);
        return MapDataCommentToDto(comment);
    }

    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.getById(commentDTO.getId());
        Employee employee = authService.getCurrentUser();
        if(comment.getEmployee().getId() == employee.getId()){
            throw new RuntimeException();
        }
        comment.setContent(commentDTO.getContent());
        commentRepository.save(comment);
        return MapDataCommentToDto(comment);
    }

    public CommentDTO MapDataCommentToDto(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        commentDTO.setEmployeeId(comment.getEmployee().getId());
        commentDTO.setEmployeeName(comment.getEmployee().getName());
        commentDTO.setId(comment.getId());
        commentDTO.setTaskID(comment.getTask().getId());
        commentDTO.setTaskName(comment.getTask().getName());
        return commentDTO;
    }

    public List<CommentDTO> getComment(Long taskId) {
        Task task = taskRepository.getById(taskId);
        return commentRepository.findCommentsByTask(task).stream().map(this::MapDataCommentToDto).collect(Collectors.toList());

    }
}
