package com.example.opentalk.repository;

import com.example.opentalk.entity.Comment;
import com.example.opentalk.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByTask(Task task);
}
