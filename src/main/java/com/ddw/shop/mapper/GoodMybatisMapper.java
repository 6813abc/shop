package com.ddw.shop.mapper;

import com.ddw.shop.dto.CategoryDto;
import com.ddw.shop.dto.GoodDto;
import com.ddw.shop.entity.Good;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Description: 商品
 * @Author: ddw
 * @Date: 2020/3/5
 * @Version:
 **/
@Repository
public interface GoodMybatisMapper {

    /**
     * 功能描述:查询商品
     *
     * @param cid
     * @param index
     * @param limit
     * @return java.util.List<com.ddw.shop.dto.GoodDto>
     * @author cyg
     * @date 2020/4/22
     */
    @Select("select g.id, g.name, g.cid, g.price, g.flag, f.code from good g join file f on g.img_id = f.id  " +
            "where g.cid = #{cid} limit #{index},#{limit}")
    List<GoodDto> findByCid(@Param("cid") Long cid,
                            @Param("index") Long index,
                            @Param("limit") Integer limit);
}
