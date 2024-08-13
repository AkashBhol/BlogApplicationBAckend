package com.example.BlogApplicationBAckend.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private int uId;

    private String email;

    private String password;

    private String name;

    private String about;
}
