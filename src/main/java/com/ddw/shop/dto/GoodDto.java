package com.ddw.shop.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ddw
 * @date 2020/4/9 17:39
 **/
@Data
public class GoodDto {

    private Long id;
    private String name;
    private Long cid;
    private String price;
    private String flag;
    private String code;
}
