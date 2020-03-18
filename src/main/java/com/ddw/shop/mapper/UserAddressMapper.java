package com.ddw.shop.mapper;

import com.ddw.shop.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Description: 用户
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface UserAddressMapper extends JpaRepository<UserAddress, String> {
}
