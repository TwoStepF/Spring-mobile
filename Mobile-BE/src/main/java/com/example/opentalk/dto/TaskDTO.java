package com.example.opentalk.dto;

import com.example.opentalk.service.AttributeEncryptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

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

    private Long projectId;

    private String projectName;

    private String employeeName;

    private Long employeeId;

    private String status;
}
