package com.example.opentalk.entity;

import com.example.opentalk.service.AttributeEncryptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Convert(converter = AttributeEncryptor.class)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
