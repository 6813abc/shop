package com.ddw.shop.service;

import com.ddw.shop.dto.UserAdd;
import com.ddw.shop.entity.File;
import com.ddw.shop.entity.User;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.FileMapper;
import com.ddw.shop.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ddw
 * @date 2020/3/17 14:06
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    FileMapper fileMapper;

    @Transactional(rollbackFor = Exception.class)
    public BaseResult addUser(UserAdd userAdd) {
        File file = new File();
        file.setCode(userAdd.getBaseCode());
        fileMapper.save(file);
        User user = new User();
        user.setImgId(file.getId());
        BeanUtils.copyProperties(userAdd, user);
        userMapper.save(user);
        return ResultUtil.success(ResultEnum.OK);
    }
}
