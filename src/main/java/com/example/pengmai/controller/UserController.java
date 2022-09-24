package com.example.pengmai.controller;

import com.example.pengmai.auth.CheckLogin;
import com.example.pengmai.domain.Result;
import com.example.pengmai.entity.User;
import com.example.pengmai.service.UserService;
import com.example.pengmai.utill.JWTUtill;
import com.example.pengmai.utill.ResultUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/User")
@Transactional
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    //验证
    @RequestMapping("/verify")
    private Result<Claims> verify(String jwt){
        Claims claims =  new JWTUtill().parseJwt(jwt);
        if (new JWTUtill().isTokenExpired(jwt)){
            return ResultUtil.error("身份过期，请重新登录！");
        }else {
            System.out.println(claims.get("identity"));
            return ResultUtil.success(claims);
        }

    }
    //登录
    @RequestMapping("/login")
    private String login(User user){
        return userService.login(user);
    }
    //注册
    @RequestMapping("/register")
    private Result<User> addUser(User user){
        if(userService.findUserByUsername(user)!=null){
            return ResultUtil.error("用户名已存在");
        }
        return ResultUtil.success(userService.updateUser(user));
    }
    //注销用户
    @RequestMapping("/deleteUser")
    private void deleteShortcut(User user){
        userService.deleteUser(user);
    }
    //查找所有用户

    @RequestMapping("/findAllUser")
    private Result<List<User>> findAll(){
        return ResultUtil.success(userService.findAllUser());
    }
    //根据用户名查找
    @RequestMapping("/findUserByName")
    private Result<User> findUserByName(User user){
        return ResultUtil.success(userService.findUserByUsername(user));
    }
}
