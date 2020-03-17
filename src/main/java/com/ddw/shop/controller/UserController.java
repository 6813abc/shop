package com.ddw.shop.controller;

import com.ddw.shop.dto.UserAdd;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ddw
 * @date 2020/3/17 14:25
 **/
@Api(tags = "用户管理")
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    @ApiOperation("注册")
    public BaseResult addUser( UserAdd userAdd) {
        return userService.addUser(userAdd);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public BaseResult loginUser(HttpServletRequest request, String phone, String password, String code) {
        return userService.loginUser(request, phone, password, code);
    }

    @GetMapping("/getCode")
    @ApiOperation("验证码")
    public BaseResult getCode(HttpServletRequest request) {
        return userService.getCode(request);
    }
}
