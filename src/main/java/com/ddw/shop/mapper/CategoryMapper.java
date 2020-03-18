package com.ddw.shop.mapper;

import com.ddw.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Description: 类目
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
public interface CategoryMapper extends JpaRepository<Category, Long> {

    List<Category> findByParentIdAndNameContaining(Long parentId, String name);

    List<Category> findByParentId(Long parentId);
}
