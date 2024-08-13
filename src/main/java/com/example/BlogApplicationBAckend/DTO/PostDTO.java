package com.example.BlogApplicationBAckend.DTO;

import com.example.BlogApplicationBAckend.Entity.Category;
import com.example.BlogApplicationBAckend.Entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDTO {
    private int id;

    private String content;

    private String about;

    private String userEmail;

    private String title;

    private Category category;

    private User user;
}
