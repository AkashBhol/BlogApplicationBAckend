package com.example.BlogApplicationBAckend.Repo;

import com.example.BlogApplicationBAckend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
    boolean existsById(int id);

    User findByEmail(String email);
}
