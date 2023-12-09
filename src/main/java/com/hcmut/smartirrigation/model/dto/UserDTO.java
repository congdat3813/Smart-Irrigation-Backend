package com.hcmut.smartirrigation.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
}