package com.ddw.shop.controller;

import com.ddw.shop.entity.Category;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.CategoryMapper;
import com.ddw.shop.mapper.CategoryMybatisMapper;
import com.ddw.shop.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ddw
 * @date 2020/3/17 14:25
 **/
@Api(tags = "类目管理")
@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryMybatisMapper categoryMybatisMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping(value = "/getCategory")
    @ApiOperation("查询类目")
    public BaseResult getCategory() {
        return categoryService.getCategory();
    }

    @GetMapping(value = "/insertCategory")
    public BaseResult insertCategory() {
        Map<String, String[]> map = categoryService.getMap();
        for (String s : map.keySet()) {
            String[] strings = map.get(s);
            if (StringUtils.isNotEmpty(s)) {
                Category category = new Category();
                category.setParentId(0L);
                category.setName(s);
                categoryMapper.save(category);
                System.out.println("插入一级目录：" + category);
                for (String s1 : strings) {
                    if (StringUtils.isNotEmpty(s1)) {
                        Category category1 = new Category();
                        category1.setParentId(category.getCid());
                        category1.setName(s1);
                        categoryMapper.save(category1);
                        System.out.println("插入二级目录：" + category1);
                    }
                }
            }
        }
        return ResultUtil.success(ResultEnum.OK);
    }

}
