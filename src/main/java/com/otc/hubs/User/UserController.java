package com.otc.hubs.User;

import com.otc.hubs.User.DTO.ForgetPasswordDto;
import com.otc.hubs.User.DTO.LoginDto;
import com.otc.hubs.User.DTO.RegisterDto;
import com.otc.hubs.commonService.SendEmail;
import com.otc.hubs.entities.User;
import com.otc.hubs.DTO.UserDTO;
import com.otc.hubs.utils.ResultResponse;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 用户管理
 * 处理用户相关的操作，包括创建、查询和更新用户信息
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;

    @Resource
    SendEmail sendEmail;


    @GetMapping("sendEmail")
    public void sendEmail(){
        sendEmail.sendSimpleEmail("2939117014@qq.com","test","test");
    }

    /**
     * 测试接口
     * @return string
     */
    @GetMapping("")
    String hello() {
        return "hello";
    }

    /**
     * 处理用户登录请求
     *
     * 该方法通过POST请求处理用户的登录操作它接收一个LoginDto对象作为参数，
     * 该对象包含了用户登录所需的信息，例如用户名和密码使用@Validated注解来
     * 校验LoginDto对象的数据合法性，确保接收到的数据符合预期
     *
     * @param loginDto 登录信息封装对象，包括用户输入的用户名和密码等信息
     * @return 返回登录结果，包含是否登录成功以及相关的消息
     */
    @PostMapping("/login")
    ResultResponse login(@Validated @RequestBody LoginDto loginDto){
        return  userService.login(loginDto);
    }


    /**
     * 处理用户注册请求
     *
     * 该方法通过POST请求接收用户信息，并调用UserService的register方法进行注册
     * 使用RequestBody注解将请求体中的JSON数据自动绑定到RegisterDto对象
     *
     * @param user RegisterDto类型的对象，包含用户注册所需的信息
     * @return ResultResponse对象，包含注册结果及可能的提示信息
     */
    @PostMapping("register")
    ResultResponse register(@RequestBody RegisterDto user) {
        return userService.register(user);
    }

    /**
     * 获取用户列表
     *
     * 通过发送GET请求到"/list"来调用此方法，它将返回所有用户的列表
     * 此方法不需要请求体或查询参数，直接返回用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    ResultResponse<List<User>> getUserList() {
        return ResultResponse.success( userService.list());
    }


    /**
     * 处理忘记密码请求的方法
     * 该方法用于用户在忘记密码时，通过验证用户信息来重置密码
     *
     * @param forgetPasswordDto 包含用户重置密码所需信息的数据传输对象，包括用户名、验证码、新密码等
     * @return 返回用户服务处理的结果，包括操作是否成功以及相关的提示信息
     */
    @PostMapping("/forgetPassword")
    ResultResponse forgetPassword(@Validated @RequestBody ForgetPasswordDto forgetPasswordDto) {
        return userService.forgetPassword(forgetPasswordDto);
    }
}
