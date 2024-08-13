package com.example.BlogApplicationBAckend.Repo;

import com.example.BlogApplicationBAckend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
