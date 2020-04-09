package com.ddw.shop.service;

import com.ddw.shop.dto.CategoryDto;
import com.ddw.shop.exception.BaseResult;
import com.ddw.shop.exception.ResultEnum;
import com.ddw.shop.exception.ResultUtil;
import com.ddw.shop.mapper.CategoryMapper;
import com.ddw.shop.mapper.CategoryMybatisMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ddw
 * @date 2020/3/17 14:06
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryMybatisMapper categoryMybatisMapper;
    private Integer length = 24;

    public BaseResult getCategory() {
        List<CategoryDto> categoryDtos = categoryMybatisMapper.findByParentId(0L, "/");
        categoryDtos = categoryDtos.size() > length ? categoryDtos.subList(0, length) : categoryDtos;
        for (CategoryDto categoryDto : categoryDtos) {
            List<CategoryDto> categoryChildes = getCategory(categoryDto.getCid());
            categoryDto.setCategoryDtos(categoryChildes);
        }
        return ResultUtil.success(ResultEnum.OK, categoryDtos);
    }

    public List<CategoryDto> getCategory(Long id) {
        List<CategoryDto> categoryChildes = categoryMybatisMapper.findByParentId(id, "/");
        categoryChildes = categoryChildes.size() > length / 3 ? categoryChildes.subList(0, length / 3) : categoryChildes;
        if (categoryChildes.size() == 0) {
            //返回空list，结束递归，下次递归就不会进入for循环
            return new ArrayList<>();
        }
        //暂时就查询一层，不继续递归
        /*for (CategoryDto categoryDto : categoryChildes) {
            categoryDto.setCategoryDtos(getCategory(categoryDto.getCid()));
        }*/
        return categoryChildes;
    }
}
