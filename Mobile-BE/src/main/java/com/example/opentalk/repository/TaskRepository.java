package com.example.opentalk.repository;

import com.example.opentalk.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task as t where t.project.id = ?1")
    List<Task> getByProjectId(long projectId);
}
