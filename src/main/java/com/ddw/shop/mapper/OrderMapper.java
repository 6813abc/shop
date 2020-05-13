package com.ddw.shop.mapper;

import com.ddw.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Description: 订单
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface OrderMapper extends JpaRepository<Order, Long> {
    List<Order> findByPhone(String phone);
}
