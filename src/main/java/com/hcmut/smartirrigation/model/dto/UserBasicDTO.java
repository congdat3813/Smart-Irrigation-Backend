package com.hcmut.smartirrigation.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserBasicDTO {
    private UUID id;
    private String fullName;
    private String avatar;
    private String role;
    private String username;
    private String accessToken;
}
