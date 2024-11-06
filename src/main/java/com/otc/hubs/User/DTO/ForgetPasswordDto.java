package com.otc.hubs.User.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tsk
 * @date 2024/11/6 0006
 */
@Getter
@Setter
public class ForgetPasswordDto {
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "captcha cannot be empty")
    private String captcha;
    @NotEmpty(message = "password cannot be empty")
    private String password;
}
