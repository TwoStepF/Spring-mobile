package com.example.opentalk.dto;

import com.example.opentalk.service.AttributeEncryptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String name;

    private String description;

    private ProjectDTO project;

    private List<EmployeeDTO> employeeDTO;
}
