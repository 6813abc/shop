package com.ddw.shop.mapper;

import com.ddw.shop.entity.Address;
import com.ddw.shop.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Description: 用户
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface AddressMapper extends JpaRepository<Address, Long> {
}
