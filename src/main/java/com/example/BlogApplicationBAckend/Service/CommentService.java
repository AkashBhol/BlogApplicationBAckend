package com.example.BlogApplicationBAckend.Service;

import com.example.BlogApplicationBAckend.DTO.CommentDTO;
import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.Result;

public interface CommentService {

    public Result createComment(CommentDTO commentDTO);

    public Result getAllComment(PageableDTO pageableDTO);

    public Result getSingleComment(int id);

    public Result deleteSingleComment(int id);

    public Result updateComment(int id,CommentDTO commentDTO);
}
