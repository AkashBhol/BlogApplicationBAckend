package com.example.BlogApplicationBAckend.DTO;

import com.example.BlogApplicationBAckend.Entity.Post;
import com.example.BlogApplicationBAckend.Entity.User;
import lombok.Data;

@Data
public class CommentDTO {

    private String comment;
    private String email;
    private Post post;
}
