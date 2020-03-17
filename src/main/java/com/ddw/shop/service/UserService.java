package com.ddw.shop.service;

import com.ddw.shop.config.JwtToken;
import com.ddw.shop.dto.UserAdd;
import com.ddw.shop.entity.File;
import com.ddw.shop.entity.User;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.FileMapper;
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
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    FileMapper fileMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private ValidateCodeUtil validateCodeUtil;

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

    public BaseResult loginUser(HttpServletRequest request, String phone, String password, String code) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            return ResultUtil.error(ResultEnum.PHONE_PASSWORD_EMPTY);
        }
        //校验用户账号密码
        User user = userMapper.findByPhone(phone);
        if (user == null) {
            return ResultUtil.error(ResultEnum.NO_USER_INFO);
        }
        if (!password.equals(user.getPassword())) {
            return ResultUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        String redisCode = jedisUtil.get(request.getRemoteAddr() + "_code");
        if (StringUtils.isEmpty(code)) {
            return ResultUtil.error(ResultEnum.CODE_EMPTY);
        }
        if (StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            return ResultUtil.error(ResultEnum.CODE_NOT_CORRECT);
        }
        Optional<File> file = fileMapper.findById(user.getImgId());
        //生成token
        String token = jwtToken.generateToken(phone);
        Map<String, String> map = new HashMap<>(4);
        map.put("token", token);
        if (file.isPresent()) {
            map.put("code", file.get().getCode());
            map.put("name", user.getName());
            map.put("phone", user.getPhone());
        }
        return ResultUtil.success(ResultEnum.OK, map);
    }

    public BaseResult getCode(HttpServletRequest request) {
        try {
            String code = validateCodeUtil.getRandomCodeBase64(request);
            return ResultUtil.success(ResultEnum.OK, code);
        } catch (Exception e) {
            log.error(e.getMessage() + "={}", e);
            return ResultUtil.error(ResultEnum.CODE_ERROR);
        }
    }
}
