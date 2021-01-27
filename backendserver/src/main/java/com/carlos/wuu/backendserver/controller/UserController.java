package com.carlos.wuu.backendserver.controller;

import com.carlos.wuu.backendserver.entity.UserInfo;
import com.carlos.wuu.backendserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("queryById")
    public UserInfo queryById(Integer id){
        return userService.queryById(id);
    }

    @PostMapping("saveUser")
    public void saveUser(@RequestBody UserInfo userInfo){
        userService.saveUser(userInfo);
    }

}
