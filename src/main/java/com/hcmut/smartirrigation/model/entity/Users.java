package com.hcmut.smartirrigation.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;
    @NotEmpty
    private String username;
    private String avatar;
    @NotEmpty
    private String password;
    private String phoneNumber;
    private String email;
    private Date createdAt;
    private Date lastLogin;
    private String fullName;
    private String role = "USER";
    @OneToMany(fetch = FetchType.LAZY)
    private List<Farm> farms;
}
