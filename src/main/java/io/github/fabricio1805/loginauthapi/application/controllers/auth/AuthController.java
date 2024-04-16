package io.github.fabricio1805.loginauthapi.application.controllers.auth;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.fabricio1805.loginauthapi.application.DTO.LoginRequestDTO;
import io.github.fabricio1805.loginauthapi.application.DTO.RegisterRequestDTO;
import io.github.fabricio1805.loginauthapi.application.DTO.ResponseDTO;
import io.github.fabricio1805.loginauthapi.application.services.security.TokenService;
import io.github.fabricio1805.loginauthapi.domain.entity.user.User;
import io.github.fabricio1805.loginauthapi.infrastructure.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequestDTO body) {
    User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(body.password(), user.getPassword())) {
      String token = this.tokenService.generateToken(user);

      return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
    }
    return ResponseEntity.badRequest().build();
  }
  
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
    Optional<User> user = this.userRepository.findByEmail(body.email());
    
    if (user.isPresent()) {
      return ResponseEntity.badRequest().build();      
    }

    User newUser = new User(body.email(), body.name(), passwordEncoder.encode(body.password()));
    System.out.println(newUser.getEmail());
    this.userRepository.save(newUser);

     String token = this.tokenService.generateToken(newUser);
      
      return ResponseEntity.ok(new ResponseDTO(newUser.getEmail(), token));
  }
  
}
