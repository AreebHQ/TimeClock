package com.example.TimeClock.Controllers;

import com.example.TimeClock.Entities.User;
import com.example.TimeClock.JWT.JwtResponse;
import com.example.TimeClock.JWT.JwtUtils;
import com.example.TimeClock.Models.LoginRequest;
import com.example.TimeClock.Models.SignUpRequest;
import com.example.TimeClock.Repositories.UserRepository;
import com.example.TimeClock.UserLoginServices.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtUtils jwtUtils;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());
    return ResponseEntity.ok(new JwtResponse(jwt,
      Long.parseLong(userDetails.getId()),
      userDetails.getUsername(),
      userDetails.getEmail(),
      roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
    if (userRepository.findByEmail(signUpRequest.getUsername()).isPresent()) {
      return ResponseEntity
        .badRequest()
        .body("Error: Username is already taken!");
    }
    if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
      return ResponseEntity
        .badRequest()
        .body("Error: Email is already in use!");
    }

    User newUser = new User();
    newUser.setUsername(signUpRequest.getUsername());
    newUser.setEmail(signUpRequest.getEmail());
    newUser.setFirstName(signUpRequest.getFirstName());
    newUser.setLastName(signUpRequest.getLastName());
    newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    newUser.setRole(signUpRequest.getRole());

    userRepository.save(newUser);
    return ResponseEntity.ok("User registered successfully!");
  }
}
