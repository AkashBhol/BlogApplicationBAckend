package com.example.BlogApplicationBAckend.Service;

import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.PostDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import org.springframework.stereotype.Service;

public interface PostService {
    public Result createPost(PostDTO postDTO);

    public Result getALlPosts(PageableDTO pageable);

    public Result getPostById(int id);

    public Result deleteById(int id);

    public Result updatePost(int id,PostDTO postDTO);

    public Result getAllsPost();
}
