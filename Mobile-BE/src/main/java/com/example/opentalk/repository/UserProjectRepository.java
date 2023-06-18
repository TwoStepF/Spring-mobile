package com.example.opentalk.repository;

import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.Project;
import com.example.opentalk.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
    List<UserProject> getUserProjectsByProject(Project project);

    List<UserProject> getUserProjectsByProjectAndEmployee(Project project, Employee employee);

    List<UserProject> getUserProjectsByEmployee(Employee employee);
}
