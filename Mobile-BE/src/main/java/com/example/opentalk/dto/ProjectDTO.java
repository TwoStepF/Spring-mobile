package com.example.opentalk.dto;

import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.Status;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String img;
    private EmployeeDTO creator;
    private List<EmployeeDTO> listEmployee;
}
