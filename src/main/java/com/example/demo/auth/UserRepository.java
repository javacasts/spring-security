package com.example.demo.auth;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {
  User findByUsername(String username);
  void save(User user);
}
