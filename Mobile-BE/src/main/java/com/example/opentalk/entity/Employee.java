package com.example.opentalk.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank
    private String name;

    @Column(length = 200)
    @NotBlank
    private String hashPassword;

    @Column(length = 50, unique = true)
    @NotBlank
    private String email;

    @Column(length = 20)
    @NotBlank
    @Builder.Default
    private String role = "USER";

    @Column
    @Builder.Default
    private Boolean actived = true;

    @OneToOne(mappedBy = "employee")
    private OTP otp;
}
