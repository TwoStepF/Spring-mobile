package com.example.opentalk.controller;

import com.example.opentalk.dto.KeyDTO;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.repository.KeyRepository;
import com.example.opentalk.service.KeyService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/key")
@AllArgsConstructor
public class ManageKey {
    private final Gson gson;
    private final KeyService keyService;
    private final PasswordEncoder passwordEncoder;

    private final KeyRepository keyRepository;

    @PostMapping()
    private ResponseEntity<?> InputKeyToDB(@RequestBody KeyDTO keyDTO){
        try {
            StatusError statusError = keyService.InputKeyToDB(keyDTO.getKey());
            return ResponseEntity.status(statusError.getStatus()).body(gson.toJson(statusError));
        } catch (StatusError e){
            return ResponseEntity.status(e.getStatus()).body(gson.toJson(e));
        }
    }

    @PostMapping("/check")
    private ResponseEntity<?> CheckKeyToStart(@RequestBody KeyDTO keyDTO){
        try {
            keyService.setKeyRepository(keyRepository);
            keyService.setPasswordEncoder(passwordEncoder);
            StatusError statusError = keyService.checkKeyToStart(keyDTO.getKey());
            return ResponseEntity.status(statusError.getStatus()).body(gson.toJson(statusError));
        } catch (StatusError e){
            System.out.println(e);
            return ResponseEntity.status(e.getStatus()).body(gson.toJson(e));
        }
    }
}
