package com.example.pengmai.auth;

import cn.hutool.crypto.digest.MD5;
import com.example.pengmai.entity.User;
import com.example.pengmai.exception.SystemException;
import com.example.pengmai.service.UserService;
import com.example.pengmai.utill.JWTUtill;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
@Aspect
@Component
public class CheckLoginAspect {
    @Autowired
    UserService userService;
    @Around("@annotation(com.example.pengmai.auth.CheckLogin)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(11111111);
        checkLogin();
        return joinPoint.proceed();
    }

    public void checkLogin() {
        //1.从request的Header中获取token（签名信息）
        // 获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 从header中获取Token
        String token = request.getHeader("token");
        //对签名进行验证
        Claims claims =  new JWTUtill().parseJwt(token);
        Boolean identity = (Boolean) claims.get("identity");
        String username = (String) claims.get("username");
        String password = (String) claims.get("password");
        String userId = (String) claims.get("userId");
        User user = new User(userId,username,password,identity);
        if (userService.findUser(user)==null||userService.login(user).equals("尚未注册")||userService.login(user).equals("账号或密码错误")){
            throw new SystemException("没有登录");
        }
        if (!identity){
            throw new SystemException("没有权限");
        }

    }
}

























