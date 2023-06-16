package com.example.opentalk.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "TokenToConfirmEmail")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String OTP;

    @OneToOne()
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;
}
