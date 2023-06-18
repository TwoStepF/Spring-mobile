package com.example.opentalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    private String content;

    private long taskID;

    private String taskName;

    private long employeeId;

    private String employeeName;
}
