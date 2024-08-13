package com.example.BlogApplicationBAckend.Service;

import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.PasswordDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.DTO.UserDTO;
import com.example.BlogApplicationBAckend.Entity.User;

public interface UserService {

    public Result createUser(UserDTO userDTO);

    public Result getAllUser(PageableDTO pageableDTO);

    public Result getSingleUser(int id);

    public Result deleteUser(int id);

    public Result updateUser(int id,UserDTO userDTO);

    public Result updatePassword( PasswordDTO passwordDTO);
}
