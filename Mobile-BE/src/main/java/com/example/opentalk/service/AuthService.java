package com.example.opentalk.service;


import com.example.opentalk.Security.JwtToken;
import com.example.opentalk.dto.CreateEmployeeRequest;
import com.example.opentalk.dto.LoginRequest;
import com.example.opentalk.entity.Employee;
import com.example.opentalk.entity.OTP;
import com.example.opentalk.entity.RefreshToken;
import com.example.opentalk.mapper.EmployeeMapper;
import com.example.opentalk.model.MailEvent;
import com.example.opentalk.model.ResponseLogin;
import com.example.opentalk.model.StatusError;
import com.example.opentalk.repository.EmployeeRepository;
import com.example.opentalk.repository.RefreshTokenRepository;
import com.example.opentalk.repository.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final Environment env;
    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtToken jwtToken;
    private final EmployeeMapper employeeMapper;
    private final OtpRepository otpRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MailService mailService;


    public void saveTokenConfirmEmail(String name){
        Employee employee = employeeRepository.findEmployeeByName(name);
        OTP otp = new OTP();
        String token = UUID.randomUUID().toString();
        otp.setOTP(token);
        otp.setEmployee(employee);
        otpRepository.save(otp);
        String mailContent = token;
        MailEvent mailEvent = new MailEvent(this, "Mã đăng nhập", mailContent, employee.getEmail());
        mailService.sendMailWelcome(mailEvent.getMailName(), mailEvent.getContent(), mailEvent.getName());
    }


    public StatusError register(CreateEmployeeRequest createEmployeeRequest) throws Throwable {
        if (employeeRepository.findEmployeeByName(createEmployeeRequest.getName()) != null)
            throw new StatusError(HttpStatus.BAD_REQUEST, "tên tài khoản người dùng đã tồn tại");
        if (employeeRepository.findEmployeeByEmail(createEmployeeRequest.getEmail()) != null)
            throw new StatusError(HttpStatus.BAD_REQUEST, "Email người dùng đã tồn tại");
        try {
            Employee e = employeeMapper.MapDtoToEmployee(createEmployeeRequest);
            employeeRepository.save(e);
            return new StatusError(HttpStatus.OK, "Đăng ký thành công, xác nhận email của bn");
        }catch (Exception e){
            System.out.println(e);
            throw new StatusError(HttpStatus.INTERNAL_SERVER_ERROR, "lỗi");
        }
    }

    public ResponseLogin login(LoginRequest loginRequest) throws StatusError {
        try{
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }catch (Exception e){
            System.out.println(e);
            throw new StatusError(HttpStatus.BAD_REQUEST, "Tài khoản hoặc mật khẩu không chính xác");
        }
        try {
            Employee employee= employeeRepository.findEmployeeNotDecript(loginRequest.getUsername());
            String token = jwtToken.generateToken(employee.getName());
            RefreshToken refreshToken = jwtToken.generateRefreshToken(employee.getName());
            return ResponseLogin.builder()
                    .status(HttpStatus.OK)
                    .authenticationToken(token)
                    .refreshToken(refreshToken.getToken())
                    .username(employee.getName())
                    .role(employee.getRole())
                    .build();
        }catch (Exception e){
            System.out.println(e);
            throw new StatusError(HttpStatus.INTERNAL_SERVER_ERROR, "lỗi server");
        }
    }

    public Employee getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return employeeRepository.findEmployeeByName(principal.getUsername());
    }



    public String refreshToken(String token) throws StatusError {
        String rfToken = refreshTokenRepository.findRefreshTokenByToken(token).getToken();
        jwtToken.validateToken(rfToken);
        String username = jwtToken.getUsernameFromJWT(rfToken);
        return jwtToken.generateToken(username);
    }

//    public ResponseLogin confirm(OTP otp){
//        otpRepository.
//    }
}
