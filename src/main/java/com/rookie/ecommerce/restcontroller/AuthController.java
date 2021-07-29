package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.Role;
import com.rookie.ecommerce.entity.RoleName;
import com.rookie.ecommerce.entity.User;
import com.rookie.ecommerce.payload.request.LoginRequest;
import com.rookie.ecommerce.payload.request.SignupRequest;
import com.rookie.ecommerce.payload.response.JwtResponse;
import com.rookie.ecommerce.payload.response.MessageResponse;
import com.rookie.ecommerce.repository.RoleRepository;
import com.rookie.ecommerce.repository.UserRepository;
import com.rookie.ecommerce.security.jwt.JwtAuthTokenFilter;
import com.rookie.ecommerce.security.jwt.JwtUtils;
import com.rookie.ecommerce.security.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final private AuthenticationManager authenticationManager;

    final private UserRepository userRepository;

    final private RoleRepository roleRepository;

    final private PasswordEncoder encoder;

    final private JwtUtils jwtUtils;

    public AuthController (AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        //check username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // if exception has not been thrown, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFullname(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User( signUpRequest.getUsername(), signUpRequest.getFullname(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        if (signUpRequest.getPhone() != null){
            user.setPhonenum(signUpRequest.getPhone());
        }

        if (signUpRequest.getAddress() != null){
            user.setAddress(signUpRequest.getAddress());
        }

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                    if (Objects.equals(role.toLowerCase(),"role_admin")) {
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    else {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
            });
        }

        user.setRoles(roles);
        user.setStatus("ENABLE");
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jwt = JwtAuthTokenFilter.parseJwt(request);
        if (jwt != null && jwtUtils.addToBlackList(jwt)){
            return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Cannot add jwt token to blacklist!"));
    }
}
