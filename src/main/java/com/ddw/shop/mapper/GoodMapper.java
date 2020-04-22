package com.ddw.shop.mapper;

import com.ddw.shop.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Description: 类目
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface GoodMapper extends JpaRepository<Good, Long> {

}
