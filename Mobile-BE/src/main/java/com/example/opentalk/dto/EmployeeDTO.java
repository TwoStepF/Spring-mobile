package com.example.opentalk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;

    private String name;

    private String email;

    private String role;

    private Boolean activated;

    private long projectId;

    private long taskId;
}
