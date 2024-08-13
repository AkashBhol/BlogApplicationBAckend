package com.example.BlogApplicationBAckend.DTO;

import lombok.Data;

@Data
public class PasswordDTO {

    private String oldPassword;

    private String newPassword;

    private String conformPassword;

    private String email;
}
