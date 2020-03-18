package com.ddw.shop.mapper;

import com.ddw.shop.dto.CategoryDto;
import com.ddw.shop.entity.Category;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Description: 类目
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
@Repository
public interface CategoryMybatisMapper {

    @Select("select c.cid,c.name,c.parent_id from category c  where parent_id = #{parentId} " +
            "and name not like #{name}")
    @Results({
            @Result(column = "cid", property = "cid"),
    })
    List<CategoryDto> findByParentId(Long parentId,String name);
}
