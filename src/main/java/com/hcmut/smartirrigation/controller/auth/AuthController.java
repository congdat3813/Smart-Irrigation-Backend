package com.hcmut.smartirrigation.controller.auth;

import com.hcmut.smartirrigation.model.dto.UserBasicDTO;
import com.hcmut.smartirrigation.model.dto.UserDTO;
import com.hcmut.smartirrigation.service.authen.JwtTokenService;
import com.hcmut.smartirrigation.service.authen.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserBasicDTO> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(authService.login(userDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserBasicDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(authService.register(userDTO));
    }
}
