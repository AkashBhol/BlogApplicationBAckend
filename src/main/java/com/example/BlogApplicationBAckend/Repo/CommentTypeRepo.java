package com.example.BlogApplicationBAckend.Repo;

import com.example.BlogApplicationBAckend.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentTypeRepo extends JpaRepository<Comment,Integer> {
}
