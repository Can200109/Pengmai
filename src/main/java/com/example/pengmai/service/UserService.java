package com.example.pengmai.service;

import com.example.pengmai.auth.CheckLogin;
import com.example.pengmai.entity.User;
import com.example.pengmai.repository.UserRepository;
import com.example.pengmai.utill.JWTUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User updateUser(User user){
        if(findUser(user)==null){
            user.setUserId(UUID.randomUUID().toString());
        }

        return userRepository.save(user);
    }
    public User findUser(User user){
        return userRepository.findUserByUserId(user.getUserId());
    }
    public void deleteUser(User user){
        userRepository.delete(findUser(user));
    }
    @CheckLogin
    public List<User> findAllUser(){
        return userRepository.findAll();
    }
    public User findUserByUsername(User user){
        return userRepository.findUserByUsername(user.getUsername());
    }
    public String login(User user) {
        User user1 = findUserByUsername(user);
        if (user1 == null) {
            //尚未注册
            return "尚未注册";
        } else if (!user.getPassword().equals(user1.getPassword())) {
            //账号或密码错误
            return "账号或密码错误";
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("username", user1.getUsername());
            map.put("password", user1.getPassword());
            map.put("identity", user1.isUserIdentity());
            map.put("userId", user1.getUserId());
            try {
                return new JWTUtill().createJWT(user1.getUserId(), user.getUsername(), 60000L * 60 * 24 * 7, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
