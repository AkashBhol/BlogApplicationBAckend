package com.example.BlogApplicationBAckend.ServiceImpl;

import com.example.BlogApplicationBAckend.BasicConfiguration.BsicUtil;
import com.example.BlogApplicationBAckend.DTO.*;
import com.example.BlogApplicationBAckend.Entity.User;
import com.example.BlogApplicationBAckend.Exception.RescourceNotFoundException;
import com.example.BlogApplicationBAckend.Repo.UserRepo;
import com.example.BlogApplicationBAckend.Service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BsicUtil implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Result createUser(UserDTO userDTO) {
        log.debug("the message is calling createUser,userDto {}", userDTO);
        User user = modelMapper.map(userDTO, User.class);
        boolean isvalid = isValid(user);
        if (!isvalid) {
            return prepareResponseObject("001", "The email is allready exist or mil is null", null);
        }
        log.debug("transfroming data userDto to user {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return prepareResponseObject("002", "User Created SuccessFully", user);
    }

    @Override
    public Result getAllUser(PageableDTO pageableDTO) {
        log.debug("The Message is for calling getAllUser {}");
        Pageable pagging = null;
        pagging = PageRequest.of(pageableDTO.getPage(), pageableDTO.getPageSize());
        Page<User> all = userRepo.findAll(pagging);
        PageableResponseVO  pageableResponseVO=new PageableResponseVO(all.getContent(),all,pageableDTO);
        log.debug("Getting all the records {}", pageableResponseVO);
        return prepareResponseObject("001", "Getting all records successfully", pageableResponseVO);
    }

    @Override
    public Result getSingleUser(int id) {
        if (isNullOrEmpty(id)) {
            return prepareResponseObject("001", "The given is not Present", null);
        }
        boolean b = userRepo.existsById(id);
        if(b){
            return prepareResponseObject("001","The given Id is Not present",null);
        }
        User user = userRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("the id is not present", "", ""));
        log.warn("The message is calling for findById {}", user);
        return prepareResponseObject("001", "The Record fetched successFully usinng the given id", user);
    }

    @Override
    public Result deleteUser(int id) {
        User user = userRepo.findById(id).orElseThrow(()->new RescourceNotFoundException("The given id is not present", "", ""));
        if(isNullOrEmpty(user)){
            return prepareResponseObject("001","the given id id not present","");
        }
        else{
            userRepo.deleteById(id);
        }
        return prepareResponseObject("001","User deleted successfully",null);
    }

    @Override
    public Result updateUser(int id, UserDTO userDTO) {
        User user = userRepo.findById(id).orElseThrow(() -> new RescourceNotFoundException("The id is not present", "", ""));
        if(isNullOrEmpty(user)){
            return prepareResponseObject("001","The user is not present","");
        }
        User map = modelMapper.map(userDTO, User.class);
        log.debug("the updated value {}",map);
        map.setId(user.getId());
        boolean valid = isValid(map);
        if(!valid){
            return prepareResponseObject("001","The Email is allready exist or null",null);
        }
        User save = userRepo.save(map);
       return  prepareResponseObject("001","Record update successfully",save);
    }

    @Override
    public Result updatePassword( PasswordDTO passwordDTO) {
        User user = userRepo.findByEmail(passwordDTO.getEmail());
        if(!isNullOrEmpty(user)) {
            if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
                userRepo.save(user);
                return prepareResponseObject("233","passwordUpdatedSuccessfully",null);
            }
            return prepareResponseObject("234","Old Password does not match",null);
        }
        return prepareResponseObject("222","User does not exist",null);
    }



    private boolean isValid(User user) {
        if (isNullOrEmpty(user.getEmail())) {
            return false; // Email is empty, return false
        }
        boolean exists = userRepo.existsByEmail(user.getEmail());
        return !exists; // Return false if email exists, true otherwise
    }
}
