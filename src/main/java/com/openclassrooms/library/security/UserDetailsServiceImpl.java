package com.openclassrooms.library.security;

import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements UserDetailsService to find a User by his username
 *
 * @see UserDetailsService
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Load a user by his username
     *
     * @param username user's username
     *
     * @return a user
     * @throws UsernameNotFoundException exception if the user isn't find
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur ayant pour pseudo " + username + " n'a pas été trouvé"));
        return UserDetailsImpl.build(user);
    }
}
