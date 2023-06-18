package com.example.opentalk.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Project")
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany
    private List<Task> tasks;
}
