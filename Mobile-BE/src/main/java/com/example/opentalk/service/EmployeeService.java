package com.example.opentalk.service;

import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.entity.Employee;
//import com.example.opentalk.mapper.EmployeeMapstruct;
import com.example.opentalk.entity.Status;
import com.example.opentalk.model.EmployeeInterface;
import com.example.opentalk.repository.EmployeeRepository;
import com.example.opentalk.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final StatusRepository statusRepository;
    public EmployeeDTO MapDataEmployeetoDTO(Employee employee) {
        return EmployeeDTO.builder()
                .name(employee.getName())
                .role(employee.getRole())
                .email(employee.getEmail())
                .activated(employee.getActivated())
                .id(employee.getId())
                .build();
    }

    public List<EmployeeDTO> filterEmployee(Integer activated, String name, String role) {
        List<Employee> employees = employeeRepository.filterEmployee(activated, name, role);
        return employees.stream()
                .map(this::MapDataEmployeetoDTO)
                .collect(Collectors.toList());
    }


    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(this::MapDataEmployeetoDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> findAndSoft() {
        Sort sort = Sort.by("name").descending();
        return employeeRepository.findAndSoft(sort).stream()
                .map(this::MapDataEmployeetoDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeInterface> find() {
        return employeeRepository.findEmployeeAndMapToITF();
    }

    public EmployeeDTO updateUserRoleOrBlock(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.getById(employeeDTO.getId());
        employee.setRole(employeeDTO.getRole());
        employee.setActivated(employeeDTO.getActivated());
        return MapDataEmployeetoDTO(employeeRepository.save(employee));
    }
}
