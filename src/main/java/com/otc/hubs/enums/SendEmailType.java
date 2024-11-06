package com.otc.hubs.enums;

import lombok.Getter;

@Getter
public enum SendEmailType {
    REGISTER("注册", "register"),
    FORGET_PASSWORD("忘记密码", "forgetPassword");

    private final String desc;
    private final String templateName;

    SendEmailType(String desc, String templateName) {
        this.desc = desc;
        this.templateName = templateName;
    }
}
