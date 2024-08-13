package com.example.BlogApplicationBAckend.ServiceImpl;

import com.example.BlogApplicationBAckend.BasicConfiguration.BsicUtil;
import com.example.BlogApplicationBAckend.DTO.*;
import com.example.BlogApplicationBAckend.Entity.Category;
import com.example.BlogApplicationBAckend.Exception.RescourceNotFoundException;
import com.example.BlogApplicationBAckend.Repo.CategoryRepo;
import com.example.BlogApplicationBAckend.Service.categoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class categoryServiceImpl extends BsicUtil implements categoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(categoryService.class);

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Result createCategory(CategoryDTO categoryDTO) {
        log.debug("calling the createCategory {}", categoryDTO);
        String date = categoryDTO.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Category map = modelMapper.map(categoryDTO, Category.class);
        Category category = Category.builder()
                .title(categoryDTO.getTitle())
                .categoryDescriptions(categoryDTO.getCategoryDescriptions())
                .date(date1).build();
        log.debug("cahnging value Dto to Entity map {}", category);
        Category save = categoryRepo.save(category);
        return prepareResponseObject("001", "Category Created Successfully", category);
    }

    @Override
    public Result getAllCategory(PageableDTO pageable, CategoryDTO categoryDTO) {
        // Pageable paging = PageRequest.of(pageable.getPage(), pageable.getPageSize());
        if (isNullOrEmpty(categoryDTO.getTitle())) {
            categoryDTO.setTitle(null);
        }
        if (isNullOrEmpty(categoryDTO.getCategoryDescriptions())) {
            categoryDTO.setCategoryDescriptions(null);
        }
        Date date = null;
        if (isNullOrEmpty(categoryDTO.getDate())) {
            categoryDTO.setDate(null);
        }
        Pageable paging = null;
        if (pageable.getPage() == 0 && pageable.getPageSize() == 0) {
            List<Category> filterRecords = categoryRepo.findByTitleOrCategoryDescriptionsOrDate(
                    categoryDTO.getTitle(),
                    categoryDTO.getCategoryDescriptions(),
                    date
            );
            paging = PageRequest.of(0, filterRecords.size());
            Page<Category> page = new PageImpl<>(filterRecords, paging, filterRecords.size());
            PageableResponseVO pageableResponseVO = new PageableResponseVO(filterRecords, page, new PageableDTO());
            return prepareResponseObject("hxhs","", pageableResponseVO);
        }
         paging = PageRequest.of(pageable.getPage(), pageable.getPageSize());
        Page<Category> all = categoryRepo.findAll(paging);
        PageableResponseVO pageableResponseVO = new PageableResponseVO(
                all.getContent(), all, pageable
        );
        log.debug("calling getAllCategory all {}", all);
        return prepareResponseObject("001", "Records Fetcged successfully", pageableResponseVO);
    }


    @Override
    public Result getSingleCategory(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "No id available", null);
        }
        Category noIdAvailable = categoryRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("no id available", "", ""));
        if (isNullOrEmpty(noIdAvailable)) {
            return prepareResponseObject("001", "no records available", noIdAvailable);
        }
        return prepareResponseObject("001", "Record fetch successFully", noIdAvailable);
    }

    @Override
    public Result deleteCategory(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "No is are availble", null);
        }
        Category noIdAvailable = categoryRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("no id available", "", ""));
        if (isNullOrEmpty(noIdAvailable)) {
            return prepareResponseObject("001", "no records are available", noIdAvailable);
        }
        categoryRepo.deleteById(id);
        return prepareResponseObject("001", "records deleted successfully", null);
    }

    @Override
    public Result updateCategory(int id, CategoryDTO categoryDTO) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "No id available", null);
        }
        Category noIdAreAvailable = categoryRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("no id are available", "", ""));
        if (isNullOrEmpty(noIdAreAvailable)) {
            return prepareResponseObject("001", "No records are vailable", noIdAreAvailable);
        }
        String date = categoryDTO.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Category category = Category.builder()
                .title(categoryDTO.getTitle())
                .categoryDescriptions(categoryDTO.getCategoryDescriptions())
                .date(date1).build();
        log.debug("converting dto to do in category {}", category);
        category.setId(noIdAreAvailable.getId());
        Category save = categoryRepo.save(category);
        return prepareResponseObject("001", "records Updated SuccessFully", save);
    }

    @Override
    public Result getAlls() {
        List<Category> all = categoryRepo.findAll();
        return prepareResponseObject("003", "All Records fetchh successsfully", all);
    }


    private static boolean hasAnyValue(CategoryDTO categoryDTO) {
        return !isNullOrEmpty(categoryDTO.getTitle()) ||
                !isNullOrEmpty(categoryDTO.getCategoryDescriptions()) ||
                !isNullOrEmpty(categoryDTO.getDate());
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";

    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault());
        dateFormat.setLenient(false);
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {

        }


        return date;
    }
}
