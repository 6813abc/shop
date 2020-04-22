package com.ddw.shop.mapper;

import com.ddw.shop.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Description: 商品
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface GoodMapper extends JpaRepository<Good, Long> {

}
