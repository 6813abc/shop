package com.ddw.shop.controller;

import com.ddw.shop.dto.CategoryDto;
import com.ddw.shop.entity.Category;
import com.ddw.shop.entity.Good;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.CategoryMapper;
import com.ddw.shop.mapper.CategoryMybatisMapper;
import com.ddw.shop.mapper.GoodMapper;
import com.ddw.shop.service.CategoryService;
import com.ddw.shop.service.GoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author ddw
 * @date 2020/3/17 14:25
 **/
@Api(tags = "商品管理")
@RestController
@CrossOrigin
@RequestMapping("good")
public class GoodController {
    @Autowired
    GoodService goodService;


    @GetMapping(value = "/getGoodByCid")
    @ApiOperation("根据类目id查询商品")
    public BaseResult getCategory(Long cid, Long page, Integer limit) {
        return ResultUtil.success(ResultEnum.OK, goodService.getGoodByCid(cid, page, limit));
    }
}
