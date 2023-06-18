package com.example.opentalk.controller;

import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.model.EmployeeInterface;
import com.example.opentalk.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.owasp.encoder.Encode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/admin/manage-employee/")
@AllArgsConstructor
public class ManageEmployeeController {

    private final EmployeeService manageEmployeeService;

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> getUserByParam(@PathParam("activated") Integer activated,
                                                            @PathParam("name") String name,
                                                            @PathParam("role") String role) {
        return ResponseEntity.status(HttpStatus.OK).body(manageEmployeeService.filterEmployee(activated, name, role));
    }


    @PutMapping("/update-role-or-block")
    public ResponseEntity<EmployeeDTO> updateUserRoleOrBlock(EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(manageEmployeeService.updateUserRoleOrBlock(employeeDTO));
    }

//    @GetMapping("/soft")
//    public List<EmployeeDTO> getUserAndSoft() {
//        return manageEmployeeService.findAndSoft();
//    }

}
