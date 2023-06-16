package com.example.opentalk.controller;


import com.example.opentalk.dto.*;
import com.example.opentalk.entity.OTP;
import com.example.opentalk.model.ResponseLogin;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.service.AuthService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final Gson gson;

    @PostMapping("/login")
    ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) throws InterruptedException {
        try {
            ResponseLogin responseLogin = authService.login(loginRequest);
            return ResponseEntity.status(responseLogin.getStatus()).body(responseLogin);
        }catch (StatusError e){
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    private ResponseEntity<StatusError> Register(@RequestBody CreateEmployeeRequest createEmployeeRequest) throws Throwable {
            StatusError statusError = authService.register(createEmployeeRequest);
            return new ResponseEntity<>(statusError, HttpStatus.OK);
    }
//
//    @PostMapping("/confirm")
//    private ResponseEntity<ResponseLogin> ConfirmEmail(@RequestBody OTP otp){
//
//    }

    @PostMapping("/refreshtoken")
    private ResponseEntity<?> RefreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try{
            String token = authService.refreshToken(refreshTokenRequest.getToken());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (StatusError e){
            return new ResponseEntity<>(e, e.getStatus());
        }
    }

}
