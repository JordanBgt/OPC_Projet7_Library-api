package com.openclassrooms.library.controller;

import com.openclassrooms.library.dao.RoleRepository;
import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.entity.ERole;
import com.openclassrooms.library.entity.Role;
import com.openclassrooms.library.entity.User;
import com.openclassrooms.library.model.JwtResponse;
import com.openclassrooms.library.model.LoginRequest;
import com.openclassrooms.library.model.MessageResponse;
import com.openclassrooms.library.model.SignupRequest;
import com.openclassrooms.library.security.JwtUtils;
import com.openclassrooms.library.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller to handle register and login requests
 */
@RestController
@RequestMapping("api/auth")
public class AuthController {

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    /**
     * Method to authenticate a user and update SecurityContext
     * URL : /api/auth/signin
     *
     * @param loginRequest the login request which contains username and password
     * @return ResponseEntity which contains JWTT and UserDetails
     *
     * @see LoginRequest
     * @see AuthenticationManager#authenticate(Authentication)
     * @see JwtUtils#generateJwtToken(Authentication)
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * Method to register a new user : create it and save it to database
     * URL : /api/auth/signup
     *
     * @param signupRequest the signup request that contains the information needed to register a user
     * @return MessageResponse which confirms the creation of the user
     *
     * @see SignupRequest
     * @see UserRepository#existsByUsername(String)
     * @see UserRepository#existsByEmail(String)
     * @see PasswordEncoder#encode(CharSequence)
     * @see MessageResponse
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Erreur : le pseudo est déjà utilisé !"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Erreur : l'email est déjà utilisé !"));
        }

        List<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Erreur : rôle non trouvé !"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("ADMIN") || role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Erreur : rôle non trouvé !"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Erreur : rôle non trouvé !"));
                    roles.add(userRole);
                }
            });
        }
        User user = new User(signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail(), roles);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré !"));
    }
}
