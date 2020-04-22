package com.ddw.shop.entity;

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
@Entity
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cid;
    private String price;
    /**
     * 上下架
     **/
    private String flag;
    private Long imgId;
}
