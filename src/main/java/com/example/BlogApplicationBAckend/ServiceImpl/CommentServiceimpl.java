package com.example.BlogApplicationBAckend.ServiceImpl;

import com.example.BlogApplicationBAckend.BasicConfiguration.BsicUtil;
import com.example.BlogApplicationBAckend.DTO.CommentDTO;
import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.PageableResponseVO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.Entity.Comment;
import com.example.BlogApplicationBAckend.Entity.CommentRepo;
import com.example.BlogApplicationBAckend.Entity.User;
import com.example.BlogApplicationBAckend.Exception.RescourceNotFoundException;
import com.example.BlogApplicationBAckend.Repo.CommentTypeRepo;
import com.example.BlogApplicationBAckend.Repo.UserRepo;
import com.example.BlogApplicationBAckend.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceimpl extends BsicUtil implements CommentService {

    @Autowired
    private CommentTypeRepo commentTypeRepo;

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(CommentServiceimpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Override

    public Result createComment(CommentDTO commentDTO) {
        log.debug("calling the createComment {}", commentDTO);
        Comment map = modelMapper.map(commentDTO, Comment.class);
        String email = commentDTO.getEmail();
        User byEmail = userRepo.findByEmail(email);
        map.setUser(byEmail);
        Comment save = commentTypeRepo.save(map);
        return prepareResponseObject("001", "Post created successsfully", save);
    }

    @Override
    public Result getAllComment(PageableDTO pageableDTO) {
        log.debug("calling the getAllComment {}");
        Pageable pagging=null;
        pagging= PageRequest.of(pageableDTO.getPage(),pageableDTO.getPageSize());
        Page<Comment> all = commentTypeRepo.findAll(pagging);
        log.debug("getting all records all {}", all);
        PageableResponseVO pageableResponseVO=new PageableResponseVO(all.getContent(),all,pageableDTO);
        return prepareResponseObject("001", "All comment fetched successfully", pageableResponseVO);
    }

    @Override
    public Result getSingleComment(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "No Id is avilable", null);
        }
        Comment theIdIsNotAvilable = commentTypeRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("the id is not avilable", "", ""));
        log.debug("calling findBYId getting records theIdIsNotAvilable {}", theIdIsNotAvilable);
        if (isNullOrEmpty(theIdIsNotAvilable)) {
            return prepareResponseObject("001", "No records are vailable", theIdIsNotAvilable);
        }
        return prepareResponseObject("001", "Record fetchedSuccessFully", theIdIsNotAvilable);
    }

    @Override
    public Result deleteSingleComment(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "NO id are available", null);
        }
        Comment comment = commentTypeRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("The given id id not avilable", "", ""));
        if (isNullOrEmpty(comment)) {
            return prepareResponseObject("001", "No record are avilable", null);
        }
        commentTypeRepo.deleteById(id);
        return prepareResponseObject("001", "Records Fetched successFully", null);
    }

    @Override
    public Result updateComment(int id, CommentDTO commentDTO) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "No given id is avilable", null);
        }
        Comment noIdIsAvailable = commentTypeRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("no id is available", "", ""));
        if (isNullOrEmpty(noIdIsAvailable)) {
            return prepareResponseObject("001", "the Given is not available", noIdIsAvailable);
        }
        Comment map = modelMapper.map(commentDTO, Comment.class);
        Comment save = commentTypeRepo.save(map);
        return prepareResponseObject("001", "Comment updated successfully", save);
    }
}
