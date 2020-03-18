package com.ddw.shop.controller;

import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.service.AddressService;
import com.ddw.shop.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getCategory")
    @ApiOperation("查询类目")
    public BaseResult getCategory() {
        return categoryService.getCategory();
    }
}
