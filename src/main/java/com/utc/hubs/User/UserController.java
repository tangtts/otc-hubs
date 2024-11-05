package com.utc.hubs.User;

import com.utc.DTO.UserDTO;
import com.utc.hubs.entities.User;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  UserService userService;

  @GetMapping("")
 String hello(){
    return "hello";
  }
  @GetMapping("/list")
 List<User> getUserList(){
    return userService.list();
  }

  @PostMapping("/addUser")
 boolean addUser(@Validated @RequestBody UserDTO user){
    return userService.addUser(user);
  }
}
