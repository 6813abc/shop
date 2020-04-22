package com.ddw.shop.service;

import com.ddw.shop.dto.CategoryDto;
import com.ddw.shop.dto.GoodDto;
import com.ddw.shop.mapper.CategoryMapper;
import com.ddw.shop.mapper.CategoryMybatisMapper;
import com.ddw.shop.mapper.GoodMapper;
import com.ddw.shop.mapper.GoodMybatisMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ddw
 * @date 2020/3/17 14:06
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryMybatisMapper categoryMybatisMapper;
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    GoodMybatisMapper copyProperties;


    public List<GoodDto> getGoodByCid(Long cid, Long page, Integer limit) {
        Long index = (page - 1) * limit;
        return copyProperties.findByCid(cid, index, limit);
    }
}
