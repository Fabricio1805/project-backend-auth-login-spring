package io.github.fabricio1805.loginauthapi.application.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.fabricio1805.loginauthapi.domain.entity.user.User;
import io.github.fabricio1805.loginauthapi.infrastructure.repository.user.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return this.userRepository.findAll();
  }
}
