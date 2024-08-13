package com.example.BlogApplicationBAckend.Service;

import com.example.BlogApplicationBAckend.DTO.CategoryDTO;
import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.Result;

public interface categoryService {

    public Result createCategory(CategoryDTO categoryDTO);

    public Result getAllCategory(PageableDTO pageable,CategoryDTO categoryDTO);

    public Result getSingleCategory(int id);

    public Result deleteCategory(int id);

    public Result updateCategory(int id,CategoryDTO categoryDTO);

    public Result getAlls();
}
