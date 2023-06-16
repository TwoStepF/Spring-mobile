package com.example.opentalk.mapper;

import com.example.opentalk.dto.CreateEmployeeRequest;
import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeMapper {
    private final PasswordEncoder passwordEncoder;


    public Employee MapDtoToEmployee(CreateEmployeeRequest createEmployeeRequest){
        String Hash_password = passwordEncoder.encode(createEmployeeRequest.getPassword());
        return Employee.builder()
                .name(createEmployeeRequest.getName())
                .email(createEmployeeRequest.getEmail())
                .hashPassword(Hash_password)
                .build();
    }


    public EmployeeDTO toDto(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }
}
