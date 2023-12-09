package com.hcmut.smartirrigation.controller.auth;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}/profile")
    public HttpEntity<String> getUserProfile() {
        return new HttpEntity<>("Hello");
    }
}
