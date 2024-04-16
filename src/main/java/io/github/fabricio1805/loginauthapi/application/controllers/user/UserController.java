package io.github.fabricio1805.loginauthapi.application.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.fabricio1805.loginauthapi.application.services.user.UserService;
import io.github.fabricio1805.loginauthapi.domain.entity.user.User;


@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping()
  public List<User> findAllUser() {
    return this.userService.findAll(); 
  }
  
}
