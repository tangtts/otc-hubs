package com.otc.hubs.Interceptor;

import com.otc.hubs.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

public class AuthHandlerInterceptor {
    @Resource()
    JwtUtil jwtUtil;



    /**
     * 权限认证的拦截操作.
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        //为空就返回错误
        String token = httpServletRequest.getHeader("token");
        if (null == token || "".equals(token.trim())) {
            return false;
        }

        Map<String, String> map = (Map<String, String>) jwtUtil.parseToken(token);
        String userId = map.get("userId");
        System.out.println("userId:"+userId);
        System.out.println(Long.parseLong(map.get("timeStamp")));
//        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));
//        //1.判断 token 是否过期
//        if (timeOfUse < refreshTime) {


//            log.info("token验证成功");
//            return true;
//        }

        //token过期就返回 token 无效.
        return  true;
    }
}