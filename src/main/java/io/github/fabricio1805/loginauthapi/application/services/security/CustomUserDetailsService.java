package io.github.fabricio1805.loginauthapi.application.services.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.fabricio1805.loginauthapi.domain.entity.user.User;
import io.github.fabricio1805.loginauthapi.infrastructure.repository.user.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
  }
  
}
