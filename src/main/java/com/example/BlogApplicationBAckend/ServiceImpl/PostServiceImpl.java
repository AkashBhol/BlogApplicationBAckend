package com.example.BlogApplicationBAckend.ServiceImpl;

import com.example.BlogApplicationBAckend.BasicConfiguration.BsicUtil;
import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.PageableResponseVO;
import com.example.BlogApplicationBAckend.DTO.PostDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.Entity.Category;
import com.example.BlogApplicationBAckend.Entity.Post;
import com.example.BlogApplicationBAckend.Entity.User;
import com.example.BlogApplicationBAckend.Exception.RescourceNotFoundException;
import com.example.BlogApplicationBAckend.Repo.CategoryRepo;
import com.example.BlogApplicationBAckend.Repo.PostRepo;
import com.example.BlogApplicationBAckend.Repo.UserRepo;
import com.example.BlogApplicationBAckend.Service.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BsicUtil implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Result createPost(PostDTO postDTO) {
        logger.debug("calling the createPost {}", postDTO);
        Post map = modelMapper.map(postDTO, Post.class);
        logger.debug("getting post data {}", map);
        String emil = postDTO.getUserEmail();
        User user = userRepo.findByEmail(emil);
        String categoryName = postDTO.getTitle();
        Category category = categoryRepo.findByTitle(categoryName);
        map.setUser(user);
        map.setCategory(category);
        Post save = postRepo.save(map);
        return prepareResponseObject("001", "Post Created success created", save);
    }

    @Override
    public Result getALlPosts(PageableDTO pageable) {
        Pageable pagging = null;
        pagging = PageRequest.of(pageable.getPage(), pageable.getPageSize());
        Page<Post> all = postRepo.findAll(pagging);
        PageableResponseVO pageableResponseVO = new PageableResponseVO(all.getContent(), all, pageable);
        logger.debug("calling and getting the {}");
        return prepareResponseObject("001", "All post fetch successFully", pageableResponseVO);
    }

    @Override
    public Result getPostById(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "no id is present", null);
        }
        Post noIdIsPresent = postRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("No id is present", "", ""));
        if (isNullOrEmpty(noIdIsPresent)) {
            return prepareResponseObject("002", "no Posts are available", "noIdIsPresent");
        }
        return prepareResponseObject("001", "Post fetched successsFully", noIdIsPresent);
    }

    @Override
    public Result deleteById(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "No id is availble", "");
        }
        Post noIdIsVailablle = postRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("no id is vailablle", "", ""));
        if (isNullOrEmpty(noIdIsVailablle)) {
            return prepareResponseObject("001", "no records are avilable", "");
        }
        postRepo.deleteById(id);
        return prepareResponseObject("001", "Record deleted successfully", "");
    }

    @Override
    public Result updatePost(int id, PostDTO postDTO) {
        logger.debug("getiing Dto post {}", postDTO);
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "no id is avilable", null);
        }
        Post noIdIsAvilable = postRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("no id is avilable", "", ""));
        if (isNullOrEmpty(noIdIsAvilable)) {
            return prepareResponseObject("001", "no records are avilable", noIdIsAvilable);
        }
        Post map = modelMapper.map(postDTO, Post.class);
        map.setId(noIdIsAvilable.getId());
        map.setCategory(postDTO.getCategory());
        Post save = postRepo.save(map);
        return prepareResponseObject("", "Post updated successs fully", save);
    }

    @Override
    public Result getAllsPost() {
        List<Post> all = postRepo.findAll();
        logger.debug("Calling to the FindAll(),{}",all);
        return prepareResponseObject("123","PostFetched successfully",all);
    }
}
