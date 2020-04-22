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
    @Autowired
    GoodMapper goodMapper;

    @GetMapping(value = "/getCategory")
    @ApiOperation("查询类目")
    public BaseResult getCategory() {
        return ResultUtil.success(ResultEnum.OK, categoryService.getCategory());
    }

    @GetMapping(value = "/insertCategory")
    @ApiOperation("用来插入数据的，别触发")
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

    @GetMapping(value = "/insertGoods")
    @ApiOperation("用来插入数据的，别触发")
    public BaseResult insertGoods() {
        int max = 30;
        int min = 10;
        Random random = new Random();
        List<CategoryDto> categoryDtos = categoryService.getCategory();
        for (CategoryDto categoryDto : categoryDtos) {
            List<CategoryDto> categoryDtos1 = categoryDto.getCategoryDtos();
            for (CategoryDto categoryDto1 : categoryDtos1) {
                int num = random.nextInt(max) % (max - min + 1) + min;
                for (int i = 0; i < num; i++) {
                    Good good = new Good();
                    good.setFlag("1");
                    good.setName(categoryDto1.getName() + i);
                    good.setCid(categoryDto1.getCid());
                    good.setImgId(4L);
                    good.setPrice(random.nextInt(1000) + "");
                    System.out.println("新增商品:" + good);
                    goodMapper.save(good);
                }
            }
        }
        return ResultUtil.success(ResultEnum.OK);
    }
}
