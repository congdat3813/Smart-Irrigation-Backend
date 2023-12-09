package com.hcmut.smartirrigation.service.authen;

import com.hcmut.smartirrigation.model.entity.SessionUser;
import com.hcmut.smartirrigation.model.entity.Users;
import com.hcmut.smartirrigation.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Not found account"));
        return new SessionUser(user);
    }

    public UserDetails loadUserById(String id) {
        try {
            Users user = userRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found id: " + id));
            return new SessionUser(user);
        } catch (Exception ex) {
            log.error("Failed to load user from server: " + ex);
            return null;
        }
    }
}

