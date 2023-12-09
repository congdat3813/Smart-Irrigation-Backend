package com.hcmut.smartirrigation.service.authen;

import com.hcmut.smartirrigation.exception.BadRequestException;
import com.hcmut.smartirrigation.model.dto.UserBasicDTO;
import com.hcmut.smartirrigation.model.dto.UserDTO;
import com.hcmut.smartirrigation.model.entity.SessionUser;
import com.hcmut.smartirrigation.model.entity.Users;
import com.hcmut.smartirrigation.model.enums.Role;
import com.hcmut.smartirrigation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    //Hard coded login setup for JWT showcase, do not use in a real application.
    public UserBasicDTO login(UserDTO request) {
        Authentication authentication = authenticate.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        SessionUser sessionUser = (SessionUser) authentication.getPrincipal();

        Users userDetail = sessionUser.user();
        UserBasicDTO userLoginResponse = new UserBasicDTO();
        userLoginResponse.setId(userDetail.getId());
        userLoginResponse.setRole(userDetail.getRole());
        userLoginResponse.setAvatar(userDetail.getAvatar());
        userLoginResponse.setUsername(userDetail.getUsername());
        userLoginResponse.setFullName(userDetail.getFullName());
        String accessToken = jwtTokenService.generateToken(userLoginResponse.getId(), userLoginResponse.getRole());
        userLoginResponse.setAccessToken(accessToken);
        userDetail.setLastLogin(new Date());
        userRepository.save(userDetail);
        return userLoginResponse;
    }

    public UserBasicDTO register(UserDTO request) {
        Users users = new Users();
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username is existed", "4001");
        }
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setUsername(request.getUsername());
        users.setRole(Role.USER.name());
        users.setCreatedAt(new Date());
        users.setFullName("New user");
        userRepository.save(users);
        return login(request);
    }
}
