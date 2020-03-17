package com.ddw.shop.mapper;

import com.ddw.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Description: 用户
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface UserMapper extends JpaRepository<User, String> {

    User findByPhone(String phone);

    void deleteByPhone(String phone);
}
