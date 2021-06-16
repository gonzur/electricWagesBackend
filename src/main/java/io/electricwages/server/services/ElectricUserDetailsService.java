package io.electricwages.server.services;

import io.electricwages.server.models.User;
import io.electricwages.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElectricUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username);
        return new UserPrincipal(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
