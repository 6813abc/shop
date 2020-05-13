package com.ddw.shop.mapper;

import com.ddw.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Description: 购物车
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface ShopMapper extends JpaRepository<Shop, Long> {
}
