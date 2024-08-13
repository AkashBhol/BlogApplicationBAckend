package com.example.BlogApplicationBAckend.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Post,Integer> {
}
