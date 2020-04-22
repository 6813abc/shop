package com.ddw.shop.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ddw
 * @date 2020/3/18 11:32
 **/
@Data
public class CategoryDto {
    private Long cid;
    private String name;
    private Long parentId;
    List<CategoryDto> categoryDtos;
}
