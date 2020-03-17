package com.ddw.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ddw
 * @date 2020/3/17 10:38
 **/
@Entity
@Data
public class User {
    @Id
    private String phone;
    private String password;
    private String name;
    private String sex;
    private String address;
    private Long imgId;
    /**
     * 余额
     **/
    private String money;
}
