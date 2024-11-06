package com.otc.hubs.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.otc.hubs.DTO.UserDTO;
import com.otc.hubs.User.DTO.ForgetPasswordDto;
import com.otc.hubs.User.DTO.LoginDto;
import com.otc.hubs.User.DTO.RegisterDto;
import com.otc.hubs.entities.User;
import com.otc.hubs.utils.ResultResponse;

public interface IUserService extends IService<User> {
    ResultResponse login(LoginDto loginDto);

    ResultResponse register(RegisterDto user);

    ResultResponse forgetPassword(ForgetPasswordDto forgetPasswordDto);
}
