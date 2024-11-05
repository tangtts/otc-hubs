package com.utc.hubs.User;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utc.DTO.UserDTO;
import com.utc.hubs.entities.User;
import com.utc.hubs.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

  @Resource
  UserMapper userMapper;
  boolean addUser(UserDTO u){
    User user = new User();
    user.setUserName(u.getUsername());
    user.setPhoneNumber(u.getPhoneNumber());
    user.setEmail(u.getEmail());
    System.out.println(user);
    userMapper.insert(user);
    return  true;
//    return save(user);
  }
}
