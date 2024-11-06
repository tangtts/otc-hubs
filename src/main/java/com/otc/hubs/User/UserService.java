package com.otc.hubs.User;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otc.hubs.User.DTO.ForgetPasswordDto;
import com.otc.hubs.User.DTO.LoginDto;
import com.otc.hubs.User.DTO.RegisterDto;
import com.otc.hubs.entities.User;
import com.otc.hubs.enums.SendEmailType;
import com.otc.hubs.mapper.UserMapper;
import com.otc.hubs.DTO.UserDTO;
import com.otc.hubs.utils.JwtUtil;
import com.otc.hubs.utils.RedisUtils;
import com.otc.hubs.utils.ResultResponse;
import com.otc.hubs.utils.StatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisUtils redisUtils;


    @Override
    public ResultResponse login(LoginDto loginDto) {
//    判断 username 是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, loginDto.getUsername()));

        if (ObjUtil.isNull(user)) {
            return ResultResponse.error(StatusEnum.SERVICE_ERROR, "用户不存在");
        }

        if (ObjUtil.equals(user.getPassword(), loginDto.getPassword())) {
            // 生成token
            String token = JwtUtil.createToken(user.getUserId());
            return ResultResponse.success(token);
        }

        return ResultResponse.success(null);
    }

    @Override
    public ResultResponse register(RegisterDto u) {
        // 校验邮箱是否被注册
        User existsUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, u.getEmail()));
        if (ObjUtil.isNotNull(existsUser)) {
            return ResultResponse.error(StatusEnum.SERVICE_ERROR, "邮箱已被注册");
        }

        String captchaKey = SendEmailType.REGISTER.getTemplateName() + ":captcha";
        // 校验 captcha
        String captcha = redisUtils.getString(captchaKey);

        if (ObjUtil.notEqual(captcha, u.getCaptcha())) {
            return ResultResponse.success("验证码不正确");
        }

        User user = new User();
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        int insert = userMapper.insert(user);
//        需要删除验证码
        redisUtils.delete(captchaKey);
        if (ObjUtil.equals(insert, 1)) {
            return ResultResponse.success("创建成功");
        } else {
            return ResultResponse.error(StatusEnum.SERVICE_ERROR, "新增失败");
        }
    }

    @Override
    public ResultResponse forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail,
                forgetPasswordDto.getEmail()));
//        用户是否存在
        if (ObjUtil.isNull(user)) {
            return ResultResponse.error(StatusEnum.SERVICE_ERROR, "用户不存在");
        }

//        判断验证码是否正确
        if (ObjUtil.notEqual(redisUtils.getString("captcha"), forgetPasswordDto.getCaptcha())) {
            return ResultResponse.error(StatusEnum.SERVICE_ERROR, "验证码不正确");
        }

        user.setPassword(forgetPasswordDto.getPassword());
        int update = userMapper.updateById(user);
        if (ObjUtil.equals(update, 1)) {
            return ResultResponse.success("修改密码成功");
        }
        return ResultResponse.error(StatusEnum.SERVICE_ERROR, "修改密码失败");
    }
}
