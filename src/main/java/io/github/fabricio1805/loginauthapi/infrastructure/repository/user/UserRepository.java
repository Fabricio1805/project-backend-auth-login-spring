package io.github.fabricio1805.loginauthapi.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.fabricio1805.loginauthapi.domain.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);
}
