package com.ddw.shop.controller;

import com.ddw.shop.dto.UserAdd;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ddw
 * @date 2020/3/17 14:25
 **/
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public BaseResult addUser( UserAdd userAdd) {
        return userService.addUser(userAdd);
    }

    @RequestMapping("/login")
    public BaseResult loginUser(HttpServletRequest request, String phone, String password, String code) {
        return userService.loginUser(request, phone, password, code);
    }

    @RequestMapping("/getCode")
    public BaseResult getCode(HttpServletRequest request) {
        return userService.getCode(request);
    }
}
