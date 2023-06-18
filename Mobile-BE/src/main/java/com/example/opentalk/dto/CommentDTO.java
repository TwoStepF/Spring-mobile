package com.example.opentalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDTO {
    private Long id;

    private String content;

    private long taskID;

    private String taskName;

    private long employeeId;

    private String employeeName;
}
