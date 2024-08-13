package com.example.BlogApplicationBAckend.Repo;

import com.example.BlogApplicationBAckend.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
//    Page<Category> findAll(Pageable pageable);

    Category findByTitle(String title);

    @Query("SELECT c FROM Category c WHERE " +
            "(COALESCE(:title, NULL) IS NULL OR c.title = :title) AND " +
            "(COALESCE(:categoryDescriptions, NULL) IS NULL OR c.categoryDescriptions = :categoryDescriptions) AND " +
            "(COALESCE(:date, NULL) IS NULL OR FUNCTION('DATE', c.date) = FUNCTION('DATE', :date))")
    List<Category> findByTitleOrCategoryDescriptionsOrDate(
            @Param("title") String title,
            @Param("categoryDescriptions") String categoryDescriptions,
            @Param("date") Date date
    );
}
