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

    @Column()
    private String image;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Employee creator;

    @OneToMany
    private List<Task> tasks;
}
