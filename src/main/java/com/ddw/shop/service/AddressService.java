package com.ddw.shop.service;

import com.ddw.shop.config.JwtToken;
import com.ddw.shop.dto.UserAdd;
import com.ddw.shop.entity.File;
import com.ddw.shop.entity.User;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.AddressMapper;
import com.ddw.shop.mapper.FileMapper;
import com.ddw.shop.mapper.UserAddressMapper;
import com.ddw.shop.mapper.UserMapper;
import com.ddw.shop.util.JedisUtil;
import com.ddw.shop.util.ValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author ddw
 * @date 2020/3/17 14:06
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AddressService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserAddressMapper userAddressMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private ValidateCodeUtil validateCodeUtil;

    public BaseResult addAddress(String phone ,String address) {
        return ResultUtil.success(ResultEnum.OK);
    }
}
