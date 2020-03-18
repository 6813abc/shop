package com.ddw.shop.controller;

import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ddw
 * @date 2020/3/17 14:25
 **/
@Api(tags = "地址管理")
@RestController
@CrossOrigin
@RequestMapping("address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    @ApiOperation("添加地址")
    public BaseResult addAddress(String phone ,String address) {
        return addressService.addAddress(phone,address);
    }
}
