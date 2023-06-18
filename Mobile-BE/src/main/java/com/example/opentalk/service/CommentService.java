package com.example.opentalk.service;

import com.example.opentalk.dto.CommentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    public CommentDTO createComment(CommentDTO commentDTO) {
        return commentDTO;
    }
}
