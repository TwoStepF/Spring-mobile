package com.example.opentalk.dto;

import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.Status;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private EmployeeDTO creator;
    private Status status;
}
